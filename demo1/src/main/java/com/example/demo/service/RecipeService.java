package com.example.demo.service;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public Map saveRecipe(Recipe recipe, BindingResult bindingResult, UserDetails userDetails){
        if(bindingResult.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        User user = userRepository.findUserByEmail(userDetails.getUsername());

        if(user == null) throw new UsernameNotFoundException("Can't find : " + userDetails.getUsername());

        recipe.setAuthor(user);
        recipeRepository.save(recipe);
        return Collections.singletonMap("id", recipe.getId());
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAllByOrderByDateDesc();
    }
    public Recipe getRecipeById(Integer id){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if(optionalRecipe.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Recipe recipe = optionalRecipe.get();
        return recipe;
    }

    public ResponseEntity deleteRecipeById(Integer id, UserDetails userDetails){

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        Recipe recipe = optionalRecipe.get();
        if(recipe == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(!authorIsCurrentUser(userDetails.getUsername(),recipe)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        recipeRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    public List<Recipe> getRecipeByCategory (@NotNull String category){
        List<Recipe> foundRecipes = null;
        foundRecipes = recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
        return foundRecipes;
    }

    public ResponseEntity updateRecipeById(Integer id, @NotNull BindingResult bindingResult, Recipe recipe, UserDetails userDetails){
        if(bindingResult.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if(optionalRecipe.isPresent()){
            if(!authorIsCurrentUser(userDetails.getUsername(),optionalRecipe.get())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            recipe.setId(id);
            recipe.setAuthor(optionalRecipe.get().getAuthor());
            recipeRepository.save(recipe);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private boolean authorIsCurrentUser(String username, Recipe recipe){
        return recipe.getAuthor() != null && username.equals(recipe.getAuthor().getEmail());
    }

}
