package com.V1desafio.API.repositories;

import com.V1desafio.API.models.AlunoModel;
import com.V1desafio.API.models.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaModel, Long> {

}
