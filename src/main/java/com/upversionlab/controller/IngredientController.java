package com.upversionlab.controller;

import com.upversionlab.model.Ingredient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rborcat on 1/3/2017.
 */
@RestController
public class IngredientController {
    private Map<Integer, Ingredient> ingredients = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Ingredient> getIngredients() {
        return ingredients.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ingredient getIngredient(@RequestParam int id) {
        return ingredients.get(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createIngredient(@RequestBody Ingredient ingredient) {
        ingredient.setId(ingredients.size());
        ingredients.put(ingredient.getId(), ingredient);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateIngredient(@RequestParam int id, @RequestBody Ingredient newIngredient) {
        Ingredient ingredient = ingredients.get(id);
        ingredient.update(newIngredient);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteIngredient(@RequestParam int id) {
        ingredients.remove(id);
    }
}
