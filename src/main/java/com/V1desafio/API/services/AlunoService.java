package com.V1desafio.API.services;

import com.V1desafio.API.models.AlunoModel;
import com.V1desafio.API.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public void cadastrarAluno(AlunoModel aluno) {
        alunoRepository.save(aluno);
    }

    public List<AlunoModel> listarAlunos() {
        return new ArrayList<>(alunoRepository.findAll());
    }

    public AlunoModel buscarAluno(Integer matricula) {
       return alunoRepository.findById(matricula)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public AlunoModel atualizarAluno(Integer matricula, AlunoModel aluno) {
        AlunoModel newAluno = alunoRepository.getById(matricula);
        newAluno.setNomeAluno(aluno.getNomeAluno());
        newAluno.setEmailAluno(aluno.getEmailAluno());
        newAluno.setIdadeAluno(aluno.getIdadeAluno());
        return alunoRepository.save(newAluno);
    }

    public AlunoModel corrigirCadastro(Integer matricula, AlunoModel correctAluno) {
        AlunoModel newAluno = alunoRepository.getById(matricula);

        if (correctAluno.getNomeAluno() != null && !correctAluno.getNomeAluno().equals(newAluno.getNomeAluno())) {
            newAluno.setNomeAluno(correctAluno.getNomeAluno());
        }
        if (correctAluno.getIdadeAluno() != null && !correctAluno.getIdadeAluno().equals(newAluno.getIdadeAluno())) {
            newAluno.setIdadeAluno(correctAluno.getIdadeAluno());
        }
        if (correctAluno.getEmailAluno() != null && !correctAluno.getEmailAluno().equals(newAluno.getEmailAluno())) {
            newAluno.setEmailAluno(correctAluno.getEmailAluno());
        }
        return alunoRepository.save(newAluno);
    }
    public boolean apagarAluno(Integer matricula) {
        if (alunoRepository.existsById(matricula)) {
            alunoRepository.deleteById(matricula);
            return true;
        } return false;
    }
}
