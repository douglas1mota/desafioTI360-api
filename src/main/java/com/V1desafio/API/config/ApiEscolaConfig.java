package com.V1desafio.API.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        description = "API Restfull para gestão de Alunos e Cursos - testes",
        title = "API ESCOLA",
        version = "1.0"))
@Configuration
public class ApiEscolaConfig {


}