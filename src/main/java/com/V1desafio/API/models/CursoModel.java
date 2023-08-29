package com.V1desafio.API.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CURSO")
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    @Column(name = "ID_CURSO")
    private Integer idCurso;
    @Column(name = "NOME_CURSO")
    private String nomeCurso;

}
