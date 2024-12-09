package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//@NoRepositoryBean
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartingWith(String startsWith);

    List<Ingredient> findAllByNameInOrderByPriceAsc(List<String> wantedNames);

    @Transactional
    void deleteAllByName(String name);

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1") //'AS' removed due to 3.3.5 err
    @Modifying
    @Transactional
    void updateIngredientsPriceBy10Percent();

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1 WHERE i.name IN :names")
    @Modifying
    @Transactional
    void updateIngredientsPricesForNames(List<String> names);
}
