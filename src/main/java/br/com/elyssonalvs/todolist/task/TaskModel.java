package br.com.elyssonalvs.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Anotação Lombok para gerar automaticamente getters, setters, toString, equals, hashCode, etc.
@Entity(name = "tb_tasks") // Indica que esta classe é uma entidade mapeada para uma tabela chamada "tb_tasks" no banco de dados
public class TaskModel {

    @Id // Marca o campo 'id' como a chave primária da entidade
    @GeneratedValue(generator = "UUID") // Configura a geração automática do valor da chave primária com UUIDs
    private UUID id; // Campo para armazenar o ID da tarefa

    private String description; // Campo para armazenar a descrição da tarefa

    @Column(length = 50) // Define o comprimento máximo da coluna 'title' como 50
    private String title; // Campo para armazenar o título da tarefa

    private LocalDateTime startAt; // Campo para armazenar a data e hora de início da tarefa
    private LocalDateTime endAt; // Campo para armazenar a data e hora de término da tarefa
    private String priority; // Campo para armazenar a prioridade da tarefa

    private UUID idUser; // Campo para armazenar o ID do usuário associado a esta tarefa

    @CreationTimestamp // Registra automaticamente a data e hora de criação da tarefa
    private LocalDateTime createdAt; // Campo que armazena a data e hora de criação da tarefa

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("Campo title deve conter no máximo 50 caracteres!");
        }
        this.title = title;
    }
}
