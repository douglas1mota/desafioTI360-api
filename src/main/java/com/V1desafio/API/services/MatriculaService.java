package com.V1desafio.API.services;

import com.V1desafio.API.models.*;
import com.V1desafio.API.repositories.AlunoRepository;
import com.V1desafio.API.repositories.CursoRepository;
import com.V1desafio.API.repositories.MatriculaRepository;
import com.V1desafio.API.repositories.criteria.TurmaRepositoryCustom;
import com.V1desafio.API.repositories.criteria.params.MatriculaFilterParam;
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


    public List<MatriculaResponseDTO> listarMatriculados() {
        List<MatriculaModel> lista = matriculaRepository.findAll();
        List<MatriculaResponseDTO> listaShow = new ArrayList<>();
        for (MatriculaModel matricula : lista) {
            MatriculaResponseDTO matDTO = new MatriculaResponseDTO();
            matDTO.setIdMatricula(matricula.getIdMat());
            matDTO.setNomeAluno(matricula.getNomeAluno());
            matDTO.setNomeCurso(matricula.getNomeCurso());
            listaShow.add(matDTO);
        }

        return listaShow;
    }

    public void realizarMatricula(@NotNull MatriculaRequestDTO matriculaDTO) {
        AlunoModel aluno = alunoRepository.findById(matriculaDTO.getAlunoMatId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        CursoModel curso = cursoRepository.findById(matriculaDTO.getCursoMatId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        MatriculaModel matricula = new MatriculaModel(aluno, curso);
        matricula.setAlunoMat(aluno);
        matricula.setCursoMat(curso);
        matricula.setNomeAluno(aluno.getNomeAluno());
        matricula.setNomeCurso(curso.getNomeCurso());
        matriculaRepository.save(matricula);
    }

    public void apagarMatricula(Integer idMat) {
        if (matriculaRepository.existsById(Long.valueOf(idMat))) {
            matriculaRepository.deleteById(Long.valueOf(idMat));
        }
    }


//http://localhost:8080/matriculas/filtro?cursoMat=1
    public List<MatriculaResponseDTO> filtrarCursos(TurmaFilterParam params) {
        List<MatriculaModel> lista = turmaRepositoryCustom.getFiltroCurso(params);
        List<MatriculaResponseDTO> listaShow = new ArrayList<>();
        for (MatriculaModel matricula : lista) {
            MatriculaResponseDTO matDTO = new MatriculaResponseDTO();
            matDTO.setIdMatricula(matricula.getIdMat());
            matDTO.setNomeAluno(matricula.getNomeAluno());
            matDTO.setNomeCurso(matricula.getNomeCurso());
            listaShow.add(matDTO);
        }

        return listaShow;
    }

    public List<MatriculaModel> filtrarMatriculas(MatriculaFilterParam params) {
        return turmaRepositoryCustom.getFiltroMatriculas(params);
    }
}