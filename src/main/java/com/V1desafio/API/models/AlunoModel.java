package com.V1desafio.API.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ALUNO")
//@SecondaryTable(name = "TB_MATRICULA")
@Schema
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ALUNO")
    @Schema(hidden = true)
    private Integer idAluno;
    @JoinColumn(name = "NOME_ALUNO", table = "TB_MATRICULA")
    private String nomeAluno;
    @Column(name = "IDADE_ALUNO")
    private Integer idadeAluno;
    @Column(name = "EMAIL_ALUNO")
    private String emailAluno;




}