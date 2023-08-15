package com.V1desafio.API.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_MATRICULA")
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
    @Column(name = "ID_MATRICULA")
    private Integer idMat;
    @ManyToOne
    public AlunoModel alunoMat;
    @ManyToOne
    public CursoModel cursoMat;
}