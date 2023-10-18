package br.com.elyssonalvs.todolist.erros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Indica que esta classe é um controlador de aconselhamento, que lida com exceções globalmente em toda a aplicação
public class ExceptionHandlerController {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  // Indica que este método lida com exceções do tipo HttpMessageNotReadableException
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    // Quando uma exceção do tipo HttpMessageNotReadableException ocorre, este método é chamado
    // Ele retorna uma resposta de erro com o status HTTP 400 (Bad Request) e a mensagem específica da 
    // causa da exceção
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
  }
}
