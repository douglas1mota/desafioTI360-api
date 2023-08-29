package com.V1desafio.API.controllers;

import com.V1desafio.API.models.AlunoModel;
import com.V1desafio.API.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/alunos")
@Tag(name = "API Escola - módulo Alunos")
@CrossOrigin(origins="*")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @PostMapping("/novoAluno")
    @Operation(summary = "Cadastra um novo aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviado com sucesso"),
            @ApiResponse(responseCode = "201", description = "Aluno cadastrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void cadastrarAluno(@RequestBody AlunoModel aluno) {
        String cep = aluno.getCep();
        RestTemplate restTemplate = new RestTemplate();
        AlunoModel tres = restTemplate.getForObject(String.format("https://viacep.com.br/ws/%s/json", cep)
                , AlunoModel.class);
        tres.setNomeAluno(aluno.getNomeAluno());
        tres.setEmailAluno(aluno.getEmailAluno());
        tres.setIdadeAluno(aluno.getIdadeAluno());
        service.cadastrarAluno(tres);
    }   
    @GetMapping(value = "")
    @Operation(summary = "Lista todos os alunos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro(s) encontrado(s)"),
            @ApiResponse(responseCode = "404", description = "Registro(s) não encontrado(s)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public List<AlunoModel> listarAlunos() {
        return new ArrayList<>(service.listarAlunos());
    }
    @GetMapping(value = "/{idAluno}")
    @Operation(summary = "Exibe um único aluno através do número do idAluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public AlunoModel buscarAluno(@PathVariable Integer idAluno) {
        return service.buscarAluno(idAluno);
    }
    @PutMapping(value = "/{idAluno}")
    @Operation(summary = "Edita todos os dados de um aluno cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Cadastro de aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void atualizarAluno(@PathVariable Integer idAluno, @RequestBody AlunoModel updtAluno) {
        service.atualizarAluno(idAluno, updtAluno);
    }
    @PatchMapping(value = "/{idAluno}")
    @Operation(summary = "Edita alguns dados de um aluno cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Campo(s) atualizado(s) com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void corrigirCadastro(@PathVariable Integer idAluno, @RequestBody AlunoModel correctAluno) {
        service.corrigirCadastro(idAluno, correctAluno);
    }
    @DeleteMapping(value = "/{idAluno}")
    @Operation(summary = "Apaga um registro de um aluno cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição enviada com sucesso"),
            @ApiResponse(responseCode = "201", description = "Registro apagado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    })
    public void apagarAluno(@PathVariable Integer idAluno) {
        AlunoModel codigo = null;
        service.apagarAluno(idAluno, codigo);
    }

}
