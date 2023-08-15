package com.V1desafio.API.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CURSO")
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CURSO")
    @Schema(hidden = true)
    private Integer idCurso;
    @JoinColumn(name = "NOME_CURSO", table = "TB_MATRICULA")
    private String nomeCurso;


}
