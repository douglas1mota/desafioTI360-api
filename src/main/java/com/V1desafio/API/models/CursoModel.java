package com.V1desafio.API.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tb_Curso")
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;
    private String nomeCurso;

}
