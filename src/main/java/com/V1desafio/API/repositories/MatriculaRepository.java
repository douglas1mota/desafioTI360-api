package com.V1desafio.API.repositories;

import com.V1desafio.API.models.AlunoModel;
import com.V1desafio.API.models.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaModel, Long> {

    @Query("SELECT m FROM MatriculaModel m WHERE m.alunoMat.idAluno = :idAluno")
    List<MatriculaModel> findByIdAluno(@Param("idAluno") Integer idAluno);
    List<MatriculaModel> findByAlunoMat(AlunoModel codigo);
}
