package com.V1desafio.API.services;

import com.V1desafio.API.models.AlunoModel;
import com.V1desafio.API.models.CursoModel;
import com.V1desafio.API.models.MatriculaModel;
import com.V1desafio.API.models.MatriculaRequestDTO;
import com.V1desafio.API.repositories.AlunoRepository;
import com.V1desafio.API.repositories.CursoRepository;
import com.V1desafio.API.repositories.MatriculaRepository;
import com.V1desafio.API.repositories.criteria.TurmaRepositoryCustom;
import com.V1desafio.API.repositories.criteria.params.TurmaFilterParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaService {
    @Autowired
    public CursoRepository cursoRepository;
    @Autowired
    public AlunoRepository alunoRepository;
    @Autowired
    public MatriculaRepository matriculaRepository;
    @Autowired
    public TurmaRepositoryCustom turmaRepositoryCustom;


    public List<MatriculaModel> listarMatriculados() {
        return matriculaRepository.findAll();
    }

    public void realizarMatricula(@NotNull MatriculaRequestDTO matriculaDTO) {
        AlunoModel aluno = alunoRepository.findById(matriculaDTO.getAlunoMatId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        CursoModel curso = cursoRepository.findById(matriculaDTO.getCursoMatId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        MatriculaModel matricula = new MatriculaModel();
        matricula.setAlunoMat(aluno);
        matricula.setCursoMat(curso);
        matriculaRepository.save(matricula);
    }

    public boolean apagarMatricula(Integer idMat) {
        if (matriculaRepository.existsById(Long.valueOf(idMat))) {
            matriculaRepository.deleteById(Long.valueOf(idMat));
            return true;
        } return false;
    }

    public List<MatriculaModel> filtrar(TurmaFilterParam params) {
        return turmaRepositoryCustom.getFiltro(params);
    }
}