/*
Nossa rota, onde tudo que o usuário fizer, vai passar por aqui
As informações do nosso usuário vem dentro do body da requisição
Body da aplicação, o spring já disponibiliza stwtus de erro.
 */

package br.com.elyssonalvs.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/users") // Mapeia as requisições que começam com "/users" para este controlador
public class UserController {

  @Autowired // Injeta uma instância de IUserRepository no controlador (gerenciamento de ciclo de vida)
  private IUserRepository userRepository;

  @PostMapping("/") // Mapeia as requisições POST para esta função
  public ResponseEntity create(@RequestBody UserModel userModel) {
    // Verifica se um usuário com o mesmo nome de usuário já existe
    var user = this.userRepository.findByUsername(userModel.getUsername());
    if (user != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente");
    }

    // Hash da senha usando BCrypt
    var passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    userModel.setPassword(passwordHashed);

    // Salva o usuário no banco de dados
    var userCreated = this.userRepository.save(userModel);

    return ResponseEntity.status(HttpStatus.OK).body(userCreated);
  }
}
 
