package com.V1desafio.API.services;

import com.V1desafio.API.models.CursoModel;
import com.V1desafio.API.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository CursoRepository;

    public void cadastrarCurso(CursoModel curso) {
        CursoRepository.save(curso);
    }

    public List<CursoModel> listarCursos() {
        return CursoRepository.findAll();
    }

    public CursoModel buscarCurso(Integer idCurso) {
        return CursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizarCurso(Integer idCurso, CursoModel Curso) {
        CursoModel newCurso = CursoRepository.getReferenceById(idCurso);
        newCurso.setNomeCurso(Curso.getNomeCurso());

        CursoRepository.save(newCurso);
    }

    public void corrigirCadastro(Integer idCurso, CursoModel correctCurso) {
        CursoModel newCurso = CursoRepository.getReferenceById(idCurso);

        if (correctCurso.getNomeCurso() != null && !correctCurso.getNomeCurso().equals(newCurso.getNomeCurso())) {
            newCurso.setNomeCurso(correctCurso.getNomeCurso());
        }

        CursoRepository.save(newCurso);
    }
    public void apagarCurso(Integer idCurso) {
        if (CursoRepository.existsById(idCurso)) {
            CursoRepository.deleteById(idCurso);
        }
    }
}
