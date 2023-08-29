package com.V1desafio.API.controllers;

import com.V1desafio.API.models.CursoModel;
import com.V1desafio.API.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/cursos")
@Tag(name = "API Escola - módulo Cursos")
@CrossOrigin(origins="*")
public class CursoController {
    @Autowired
    private CursoService service;

    @PostMapping("/novoCurso")
    @Operation(summary = "Cadastra um novo curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviado com sucesso"),
            @ApiResponse(responseCode = "201", description = "Curso cadastrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void cadastrarCurso(@RequestBody CursoModel curso) {
        service.cadastrarCurso(curso);
    }
    @GetMapping(value = "")
    @Operation(summary = "Lista todos os cursos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro(s) encontrado(s)"),
            @ApiResponse(responseCode = "404", description = "Registro(s) não encontrado(s)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public List<CursoModel> listarCursos() {
        return new ArrayList<>(service.listarCursos());
    }
    @GetMapping(value = "/{idCurso}")
    @Operation(summary = "Exibe um único curso através do número da idCurso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public CursoModel buscarCurso(@PathVariable Integer idCurso) {
        return service.buscarCurso(idCurso);
    }
    @PutMapping(value = "/{idCurso}")
    @Operation(summary = "Edita todos os dados de um curso cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Cadastro de curso atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void atualizarCurso(@PathVariable Integer idCurso, @RequestBody CursoModel updtCurso) {
        service.atualizarCurso(idCurso, updtCurso);
    }
   /* @PatchMapping(value = "/{idCurso}")
    @Operation(summary = "Edita alguns dados de um curso cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Campo(s) atualizado(s) com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void corrigirCadastro(@PathVariable Integer idCurso, @RequestBody CursoModel correctCurso) {
        service.corrigirCadastro(idCurso, correctCurso);
    }*/
    @DeleteMapping(value = "/{idCurso}")
    @Operation(summary = "Apaga um registro de um curso cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Registro apagado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void apagarCurso(@PathVariable Integer idCurso) {
        service.apagarCurso(idCurso);
    }

}