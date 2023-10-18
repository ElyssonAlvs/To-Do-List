/*
* GET - Buscar uma informação
* POST -> Adicionar um dado/info
* PUT -> Alterar um dado/info (ou mais)
* DELETE -> Remover um dado
* PATCH -> Alterar somente uma parte da info/dado
*
 */
package br.com.elyssonalvs.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotação que indica que esta é a classe principal da aplicação Spring Boot
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }
}

