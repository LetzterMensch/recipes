package com.example.demo.repository;

import com.example.demo.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Integer> {

    List<Recipe> findAllByOrderByDateDesc();

    List<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);
}
