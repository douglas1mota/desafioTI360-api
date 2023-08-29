package com.V1desafio.API.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_MATRICULA")
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    @Column(name = "ID_MAT")
    private Integer idMat;
    @ManyToOne
    public AlunoModel alunoMat;
    @ManyToOne
    public CursoModel cursoMat;

    public String nomeAluno;

    public String nomeCurso;



    public MatriculaModel(AlunoModel aluno, CursoModel curso) {
    }
}

