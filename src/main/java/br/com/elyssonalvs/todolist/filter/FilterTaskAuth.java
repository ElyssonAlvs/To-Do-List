package br.com.elyssonalvs.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.elyssonalvs.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Indica que esta classe é um componente gerenciado pelo Spring
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired // Injeta uma instância de IUserRepository no filtro (gerenciamento de ciclo de vida)
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        // Verifica se a requisição está relacionada a tarefas ("/tasks/")
        if (servletPath.startsWith("/tasks/")) {
            var authorization = request.getHeader("Authorization");
            System.out.println("Authorization");
            System.out.println(authorization);

            // Decodifica as credenciais de autenticação Basic
            var authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Busca o usuário com base no nome de usuário (username)
            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                // Se o usuário não for encontrado, retorna um erro de autenticação
                response.sendError(401);
            } else {
                // Verifica a senha do usuário usando BCrypt
                var passwordVerify = BCrypt.verifyer().
                        verify(password.toCharArray(), user.getPassword());

                if (passwordVerify.verified) {
                    // Se a senha estiver correta, define o ID do usuário no request e permite a continuação da cadeia de filtros
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    // Se a senha estiver incorreta, retorna um erro de autenticação
                    response.sendError(401);
                }
            }
        } else {
            // Se a requisição não está relacionada a tarefas, permite a continuação da cadeia de filtros
            filterChain.doFilter(request, response);
        }
    }
}
