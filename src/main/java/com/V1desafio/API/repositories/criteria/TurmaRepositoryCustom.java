package com.V1desafio.API.repositories.criteria;

import com.V1desafio.API.models.MatriculaModel;
import com.V1desafio.API.repositories.criteria.params.MatriculaFilterParam;
import com.V1desafio.API.repositories.criteria.params.TurmaFilterParam;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TurmaRepositoryCustom {
    List<MatriculaModel> getFiltroCurso(TurmaFilterParam params);
    List<MatriculaModel> getFiltroMatriculas(MatriculaFilterParam params);
}
