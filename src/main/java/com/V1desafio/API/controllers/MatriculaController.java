package com.V1desafio.API.controllers;

import com.V1desafio.API.models.MatriculaModel;
import com.V1desafio.API.models.MatriculaRequestDTO;
import com.V1desafio.API.repositories.criteria.params.TurmaFilterParam;
import com.V1desafio.API.services.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService service;

    @GetMapping("")
    public List<MatriculaModel> buscarMatriculados() {
        return new ArrayList<>(service.listarMatriculados());
    }
    @PostMapping("/novaMatricula")
    public void realizarMatricula(@RequestBody MatriculaRequestDTO matriculaDTO) {
        service.realizarMatricula(matriculaDTO);
    }
    @DeleteMapping(value = "/{idMat}")
    @Operation(summary = "Apaga uma matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Matrícula apagada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void apagarMatricula(@PathVariable Integer idMat) {
        service.apagarMatricula(idMat);
    }

    @GetMapping(value = "/filtro")
    @Operation(summary = "Exibe todos os alunos matriculados em um curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno(s) encontrado(s)"),
            @ApiResponse(responseCode = "404", description = "Curso sem alunos matriculado(s)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public ResponseEntity filtrar(TurmaFilterParam params) {
        return new ResponseEntity(service.filtrar(params), HttpStatus.OK);
    }


}