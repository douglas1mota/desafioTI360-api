package com.V1desafio.API.controllers;

import com.V1desafio.API.models.CursoModel;
import com.V1desafio.API.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/cursos")
public class CursoController {
    @Autowired
    private CursoService service;


    @PostMapping("/novoCurso")
    public ResponseEntity cadastrarCurso(@RequestBody CursoModel curso) {
        service.cadastrarCurso(curso);
        return new ResponseEntity(curso, HttpStatus.OK);
    }
    @GetMapping(value = "")
    public ResponseEntity<List<CursoModel>> listarCursos() {
        return new ResponseEntity(service.listarCursos(), HttpStatus.OK);
    }

    @GetMapping(value = "/{idCurso}")
    public ResponseEntity<CursoModel> buscarCurso(@PathVariable Integer idCurso) {

        return new ResponseEntity(service.buscarCurso(idCurso), HttpStatus.OK);
    }

    @PutMapping(value = "/{idCurso}")
    public ResponseEntity<Void> atualizarCurso(@PathVariable Integer idCurso, @RequestBody CursoModel updtCurso) {
        CursoModel newCurso = service.atualizarCurso(idCurso, updtCurso);
        return new ResponseEntity(newCurso, HttpStatus.OK);
    }

    @PatchMapping(value = "/{idCurso}")
    public ResponseEntity<Void> corrigirCadastro(@PathVariable Integer idCurso, @RequestBody CursoModel correctCurso) {
        CursoModel newCurso = service.corrigirCadastro(idCurso, correctCurso);
        return new ResponseEntity(newCurso, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idCurso}")
    public ResponseEntity<Void> apagarCurso(@PathVariable Integer idCurso) {
        if (service.apagarCurso(idCurso)) {
            return new ResponseEntity("Registro apagado com sucesso", HttpStatus.OK);
        } return new ResponseEntity("Registro não encontrado", HttpStatus.NOT_FOUND);

    }

}