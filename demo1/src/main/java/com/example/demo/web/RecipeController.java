package com.example.demo.web;

import com.example.demo.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
public class RecipeController {
    private int ID;
    private Map<String, Recipe> db = new HashMap<>(Map.of("1",new Recipe("1","Fresh Mint Tea",
            "Light, aromatic and refreshing beverage, ...",
            "boiled water, honey, fresh mint leaves",
            "1) Boil water. 2) Pour boiling hot water into a mug. 3) Add fresh mint leaves. "+
                    "4) Mix and let the mint leaves seep for 3-5 minutes. 5) Add honey and mix again.")));

    @GetMapping("/recipes")
    public Collection<Recipe> getDb() {
        return db.values();
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable String id) {
        Recipe recipe = db.get(id);
        if(recipe == null ) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return recipe;
    }

    @DeleteMapping("/recipes/{id}")
    public String deleteRecipe(@PathVariable String id){
        if(db.get(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        db.remove(id);
        return "Removed successfully !";
    }
    /* Using RequestBody*/
    @PostMapping("/recipes")
    public String createRecipe(@RequestBody @Valid Recipe recipe) {
        recipe.setId(UUID.randomUUID().toString());
        db.put(recipe.getId(),recipe);
        return "Successful" + db.containsValue(recipe);
    }
}
