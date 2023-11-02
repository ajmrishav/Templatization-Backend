package com.example.TemplateFieldMapping.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemplateRepository{
    @Autowired
    private EntityManager entityManager;

    public List<Object[]> executeQuery(String customquery) {
        Query q = (Query) entityManager.createNativeQuery(customquery);
        List<Object[]> results = q.getResultList();
        return results;
    }
    }
