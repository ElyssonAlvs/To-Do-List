package br.com.elyssonalvs.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    // Esta interface estende JpaRepository, onde UserModel é a entidade que estamos tratando e UUID é o tipo da chave primária.

    UserModel findByUsername(String username);
    // Este método é gerado automaticamente pelo Spring Data JPA com base no nome do método.
    // Ele permite encontrar um usuário pelo nome de usuário (username) sem a necessidade de 
    //escrever uma consulta SQL personalizada.
    // Spring Data JPA cuida da implementação por trás dos panos.

    // Além dos métodos padrão fornecidos pelo JpaRepository, você pode definir métodos personalizados para acessar seus dados conforme necessário.
}
