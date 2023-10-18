package br.com.elyssonalvs.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data // Anotação Lombok para gerar automaticamente getters, setters, toString, equals, hashCode, etc.
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os campos
@Entity(name = "tb_users") // Indica que esta classe é uma entidade mapeada para uma tabela chamada "tb_users" no banco de dados
public class UserModel {

    @Id // Marca o campo 'id' como a chave primária da entidade
    @GeneratedValue(generator = "UUID") // Configura a geração automática do valor da chave primária com UUIDs
    private UUID id;

    @Column(unique = true) // Configura a coluna 'username' como única no banco de dados
    private String username; // Campo para armazenar o nome de usuário
    private String name; // Campo para armazenar o nome do usuário
    private String password; // Campo para armazenar a senha do usuário

    @CreationTimestamp // Registra automaticamente a data e hora de criação de um registro
    private LocalDateTime createdAt; // Campo que armazena a data e hora de criação do registro
}
