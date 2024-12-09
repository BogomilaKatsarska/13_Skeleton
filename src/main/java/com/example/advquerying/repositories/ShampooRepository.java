package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    List<Shampoo> findAllByBrand(String brand);
    List<Shampoo> findAllByBrandAndSize(String brand, Size size);

    List<Shampoo> findAllBySizeOrderByIdAsc(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    List<Shampoo> findAllBySizeOrLabelId(Size size, long labelId);

    List<Shampoo> findAllByLabel_TitleContaining(String title);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countByPriceLessThan(BigDecimal price);

    List<Shampoo> findAllByIngredientsNameIn(List<String> names);

    @Query("SELECT s FROM Shampoo AS s "+
    "JOIN s.ingredients AS i " +
    "WHERE i.name IN :names")
    List<Shampoo> findAllByIngredientsNameInQuery(List<String> names);


    @Query("SELECT s FROM Shampoo AS s " +
    "WHERE SIZE(s.ingredients) < :count")
    List<Shampoo> findAllByIngredientsCountLessThan(int count);

}
