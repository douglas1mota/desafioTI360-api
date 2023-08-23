package com.V1desafio.API.repositories.criteria.impl;

import com.V1desafio.API.models.CursoModel;
import com.V1desafio.API.models.MatriculaModel;
import com.V1desafio.API.repositories.criteria.TurmaRepositoryCustom;
import com.V1desafio.API.repositories.criteria.params.TurmaFilterParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TurmaRepositoryCustomImpl implements TurmaRepositoryCustom {

    private final EntityManager entityManager;

    public TurmaRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MatriculaModel> getFiltro(TurmaFilterParam params) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<MatriculaModel> query = criteriaBuilder.createQuery(MatriculaModel.class);
        Root<MatriculaModel> matricula = query.from(MatriculaModel.class);
        List<Predicate> predicates = new ArrayList<>();
        if (params.getCursoMat() != null) {
            Join<MatriculaModel, CursoModel> cursoJoin = matricula.join("cursoMat");
            predicates.add(criteriaBuilder.equal(cursoJoin.get("id"), params.getCursoMat()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(Predicate[]::new));
        }

        TypedQuery<MatriculaModel> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }
}
