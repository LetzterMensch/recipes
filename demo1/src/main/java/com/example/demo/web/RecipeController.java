package com.example.demo.web;

import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(@Autowired RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public Collection<Recipe> getDb() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/recipes/search/")
    public Collection<Recipe> searchRecipe(@RequestParam(required = false) String category){
        return recipeService.getRecipeByCategory(category);
    }
    @DeleteMapping("/recipes/{id}")
    public String deleteRecipe(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails){
        recipeService.deleteRecipeById(id,userDetails);
        return "Removed successfully !";
    }
    /* Using RequestBody*/
    @PostMapping("/recipes")
    public Map createRecipe(@RequestBody @Valid Recipe recipe,
                            @AuthenticationPrincipal UserDetails userDetails,
                            BindingResult bindingResult) {
        return recipeService.saveRecipe(recipe,bindingResult,userDetails);
    }

    @PutMapping("recipes/{id}")
    public ResponseEntity updateRecipe(@PathVariable Integer id,
                                       @Valid @RequestBody Recipe recipe,
                                       BindingResult bindingResult,
                                       @AuthenticationPrincipal UserDetails userDetails)
    {
        return recipeService.updateRecipeById(id,bindingResult,recipe,userDetails);
    }
}
