package br.com.elyssonalvs.todolist.task;

import br.com.elyssonalvs.todolist.utils.utils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/tasks") // Mapeia as requisições que começam com "/tasks" para este controlador
public class TaskController {

  @Autowired // Injeta uma instância de ITaskRepository no controlador (gerenciamento de ciclo de vida)
  private ITaskRepository taskRepository;

  @PostMapping("/") // Mapeia as requisições POST para esta função
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    // Obtém o ID do usuário a partir do request (pressupõe-se que está configurado anteriormente)
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);

    // Obtém a data e hora atual
    var currentDate = LocalDateTime.now();

    // Verifica se a data de início ou término da tarefa é válida
    if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início/término deve ser maior do que a data atual");
    }

    // Verifica se a data de início é anterior à data de término
    if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término!");
    }

    // Salva a tarefa no banco de dados
    var task = this.taskRepository.save(taskModel);

    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @GetMapping("/") // Mapeia as requisições GET para esta função
  public List<TaskModel> list(HttpServletRequest request) {
    // Obtém o ID do usuário a partir do request (pressupõe-se que está configurado anteriormente)
    var idUser = request.getAttribute("idUser");

    // Obtém as tarefas associadas a este usuário
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);

    return tasks;
  }

  @PutMapping("/{id}") // Mapeia as requisições PUT para esta função
  public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
    // Obtém a tarefa pelo ID
    var task = this.taskRepository.findById(id).orElse(null);

    // Verifica se a tarefa existe
    if (task == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada!");
    }

    // Obtém o ID do usuário a partir do request (pressupõe-se que está configurado anteriormente)
    var idUser = request.getAttribute("idUser");

    // Verifica se o usuário tem permissão para alterar esta tarefa
    if (!task.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário sem permissão para alterar esta tarefa!");
    }

    // Copia as propriedades não nulas da nova tarefa para a tarefa existente
    utils.copyNonNullProperties(taskModel, task);

    // Salva a tarefa atualizada no banco de dados
    var taskUpdated = this.taskRepository.save(task);

    return ResponseEntity.ok().body(taskUpdated);
  }
}
