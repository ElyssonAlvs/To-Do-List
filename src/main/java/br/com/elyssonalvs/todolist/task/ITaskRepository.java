package br.com.elyssonalvs.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    // Esta interface estende JpaRepository, onde TaskModel é a entidade que estamos tratando e UUID é o tipo da chave primária.

    List<TaskModel> findByIdUser(UUID id);
    // Este método é definido manualmente e permite encontrar todas as tarefas 
    // associadas a um usuário com base no ID do usuário.
    // Não é necessário escrever uma consulta SQL personalizada, pois o Spring Data 
    // JPA cuida da implementação por trás dos panos.

    // Além dos métodos padrão fornecidos pelo JpaRepository, você pode definir métodos personalizados para acessar seus dados conforme necessário.
}
