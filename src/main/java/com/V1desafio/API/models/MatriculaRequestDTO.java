package com.V1desafio.API.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
public class MatriculaRequestDTO {
    public Integer alunoMatId;
    public Integer cursoMatId;



}