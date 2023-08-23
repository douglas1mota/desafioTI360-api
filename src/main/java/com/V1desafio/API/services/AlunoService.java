package com.V1desafio.API.services;

import com.V1desafio.API.models.AlunoModel;
import com.V1desafio.API.models.MatriculaModel;
import com.V1desafio.API.repositories.AlunoRepository;
import com.V1desafio.API.repositories.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;

    public void cadastrarAluno(AlunoModel aluno) {
       // AlunoModel newAluno = new AlunoModel();
        //newAluno.setLocalidade(aluno.getLocalidade());
        alunoRepository.save(aluno);
    }

    public List<AlunoModel> listarAlunos() {
        return new ArrayList<>(alunoRepository.findAll());
    }

    public AlunoModel buscarAluno(Integer idAluno) {
       return alunoRepository.findById(idAluno)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizarAluno(Integer idAluno, AlunoModel aluno) {
        AlunoModel newAluno = alunoRepository.getReferenceById(idAluno);
        newAluno.setNomeAluno(aluno.getNomeAluno());
        newAluno.setEmailAluno(aluno.getEmailAluno());
        newAluno.setIdadeAluno(aluno.getIdadeAluno());
        alunoRepository.save(newAluno);
    }

    public void corrigirCadastro(Integer idAluno, AlunoModel correctAluno) {
        AlunoModel newAluno = alunoRepository.getReferenceById(idAluno);

        if (correctAluno.getNomeAluno() != null && !correctAluno.getNomeAluno().equals(newAluno.getNomeAluno())) {
            newAluno.setNomeAluno(correctAluno.getNomeAluno());
        }
        if (correctAluno.getIdadeAluno() != null && !correctAluno.getIdadeAluno().equals(newAluno.getIdadeAluno())) {
            newAluno.setIdadeAluno(correctAluno.getIdadeAluno());
        }
        if (correctAluno.getEmailAluno() != null && !correctAluno.getEmailAluno().equals(newAluno.getEmailAluno())) {
            newAluno.setEmailAluno(correctAluno.getEmailAluno());
        }
        alunoRepository.save(newAluno);
    }
    public void apagarAluno(Integer idAluno, AlunoModel codigo) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findById(idAluno);

        if (alunoOptional.isPresent()) {
            List<MatriculaModel> matriculasDoAluno = matriculaRepository.findByIdAluno(idAluno);

            if (!matriculasDoAluno.isEmpty()) {
                matriculaRepository.deleteAll(matriculasDoAluno);
            }

            alunoRepository.deleteById(idAluno);
        }

    }

}
