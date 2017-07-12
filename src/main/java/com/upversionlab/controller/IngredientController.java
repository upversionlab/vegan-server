package com.upversionlab.controller;

import com.upversionlab.exception.EntityNotFoundException;
import com.upversionlab.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private static final String INGREDIENT_RESOURCE_NAME = Ingredient.class.getSimpleName();

    private final Map<Integer, Ingredient> ingredients;

    @Autowired
    public IngredientController(Map<Integer, Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Ingredient> getIngredients() {
        return ingredients.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ingredient getIngredient(@PathVariable int id) {
        return getIngredientById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        ingredient.setId(ingredients.size());
        ingredients.put(ingredient.getId(), ingredient);
        return ingredient;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Ingredient updateIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient) {
        Ingredient ingredient = getIngredientById(id);
        ingredient.update(newIngredient);
        return ingredient;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteIngredient(@PathVariable int id) {
        ingredients.remove(getIngredientById(id));
    }

    private Ingredient getIngredientById(int id) {
        Ingredient ingredient = ingredients.get(id);
        if (ingredient != null) {
            return ingredient;
        } else {
            throw new EntityNotFoundException(INGREDIENT_RESOURCE_NAME + " " + id + " not found!");
        }
    }
}
