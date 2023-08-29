package com.V1desafio.API.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ALUNO")
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUNO")
    @Schema(hidden = true)
    private Integer idAluno;
    @Column(name = "NOME_ALUNO")
    private String nomeAluno;
    @Column(name = "IDADE_ALUNO")
    private Integer idadeAluno;
    @Column(name = "EMAIL_ALUNO", unique=true)
    private String emailAluno;
    @Column(name = "CEP_ALUNO")
    private String cep;
    @Schema(hidden = true)
    private String logradouro;
    @Schema(hidden = true)
    private String localidade;
    @Schema(hidden = true)
    private String bairro;
    @Schema(hidden = true)
    private String uf;

}