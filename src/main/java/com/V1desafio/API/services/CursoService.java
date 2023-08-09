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
        List<CursoModel> lista = CursoRepository.findAll();
        return lista;
    }

    public CursoModel buscarCurso(Integer idCurso) {
        return CursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public CursoModel atualizarCurso(Integer idCurso, CursoModel Curso) {
        CursoModel newCurso = CursoRepository.getById(idCurso);
        newCurso.setNomeCurso(Curso.getNomeCurso());

        return CursoRepository.save(newCurso);
    }

    public CursoModel corrigirCadastro(Integer idCurso, CursoModel correctCurso) {
        CursoModel newCurso = CursoRepository.getById(idCurso);

        if (correctCurso.getNomeCurso() != null && !correctCurso.getNomeCurso().equals(newCurso.getNomeCurso())) {
            newCurso.setNomeCurso(correctCurso.getNomeCurso());
        }

        return CursoRepository.save(newCurso);
    }
    public boolean apagarCurso(Integer idCurso) {
        if (CursoRepository.existsById(idCurso)) {
            CursoRepository.deleteById(idCurso);
            return true;
        } return false;
    }
}
