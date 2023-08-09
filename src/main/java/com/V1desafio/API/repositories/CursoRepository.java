package com.V1desafio.API.repositories;

import com.V1desafio.API.models.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoModel, Integer> {
}
