package com.escola;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info (
				title = "API-Cursos",
				version = "1.0",
				description = "Documentando uma API para matrícula de alunos em curso",
				contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
)
public class EscolaDeProgramacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaDeProgramacaoApplication.class, args);
	}

}
