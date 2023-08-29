package com.V1desafio.API.controllers;

import com.V1desafio.API.models.MatriculaModel;
import com.V1desafio.API.models.MatriculaRequestDTO;
import com.V1desafio.API.models.MatriculaResponseDTO;
import com.V1desafio.API.repositories.criteria.params.MatriculaFilterParam;
import com.V1desafio.API.repositories.criteria.params.TurmaFilterParam;
import com.V1desafio.API.services.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
@Tag(name = "API Escola - módulo Matrículas")
@CrossOrigin(origins="*")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping("")
    public List<MatriculaResponseDTO> buscarMatriculados() {
        return new ArrayList<>(matriculaService.listarMatriculados());
    }

    @PostMapping("/novaMatricula")
    public void realizarMatricula(@RequestBody MatriculaRequestDTO matriculaDTO) {
        matriculaService.realizarMatricula(matriculaDTO);
    }

    @DeleteMapping(value = "/{idMat}")
    @Operation(summary = "Apaga uma matrícula específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Matrícula apagada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void apagarMatricula(@PathVariable Integer idMat) {
        matriculaService.apagarMatricula(idMat);
    }

    @GetMapping(value = "/filtroCurso") // http://localhost:8080/matriculas/filtroCurso?cursoMat=1
    @Operation(summary = "Exibe todos os alunos matriculados em um curso específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno(s) encontrado(s)"),
            @ApiResponse(responseCode = "404", description = "Curso sem alunos matriculado(s)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public ResponseEntity filtrarTurmas(TurmaFilterParam params) {
        return new ResponseEntity(matriculaService.filtrarCursos(params), HttpStatus.OK);
    }
    @GetMapping(value = "/filtroMatriculas") // http://localhost:8080/matriculas/filtroMatriculas?alunoMat=1
    @Operation(summary = "Exibe todas as matrículas de um aluno em específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matriculas(s) encontrada(s)"),
            @ApiResponse(responseCode = "404", description = "Aluno não possui matricula"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public ResponseEntity filtrarMatriculas(MatriculaFilterParam params) {
        return new ResponseEntity(matriculaService.filtrarMatriculas(params), HttpStatus.OK);
    }


}