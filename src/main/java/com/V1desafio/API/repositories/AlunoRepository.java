package com.V1desafio.API.repositories;

import com.V1desafio.API.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {
}
