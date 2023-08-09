package com.V1desafio.API.controllers;

import com.V1desafio.API.models.AlunoModel;
import com.V1desafio.API.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/alunos")
public class AlunoController {
    @Autowired
    private AlunoService service;


    @PostMapping("/novoAluno")
    public ResponseEntity cadastrarAluno(@RequestBody AlunoModel aluno) {
        service.cadastrarAluno(aluno);
        return new ResponseEntity(aluno, HttpStatus.OK);
    }
    @GetMapping(value = "")
    public ResponseEntity<List<AlunoModel>> listarAlunos() {
        return new ResponseEntity(service.listarAlunos(), HttpStatus.OK);
    }

    @GetMapping(value = "/{matricula}")
    public ResponseEntity<AlunoModel> buscarAluno(@PathVariable Integer matricula) {

        return new ResponseEntity(service.buscarAluno(matricula), HttpStatus.OK);
    }

    @PutMapping(value = "/{matricula}")
    public ResponseEntity<Void> atualizarAluno(@PathVariable Integer matricula, @RequestBody AlunoModel updtAluno) {
        AlunoModel newAluno = service.atualizarAluno(matricula, updtAluno);
        return new ResponseEntity(newAluno, HttpStatus.OK);
    }

    @PatchMapping(value = "/{matricula}")
    public ResponseEntity<Void> corrigirCadastro(@PathVariable Integer matricula, @RequestBody AlunoModel correctAluno) {
        AlunoModel newAluno = service.corrigirCadastro(matricula, correctAluno);
        return new ResponseEntity(newAluno, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{matricula}")
    public ResponseEntity<Void> apagarAluno(@PathVariable Integer matricula) {
        if (service.apagarAluno(matricula)) {
            return new ResponseEntity("Registro apagado com sucesso", HttpStatus.OK);
        } return new ResponseEntity("Registro não encontrado", HttpStatus.NOT_FOUND);

    }

}
