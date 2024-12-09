package com.example.advquerying.impl;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.CustomShampooRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CustomShampooRepositoryImpl implements CustomShampooRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Shampoo getFirstShampoo(){
        return entityManager.find(Shampoo.class, 1L);
    }

    @Transactional
    public void create(Shampoo shampoo) {
        entityManager.persist(shampoo);
    }
}
