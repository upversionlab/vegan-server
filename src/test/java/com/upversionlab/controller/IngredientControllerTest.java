package com.upversionlab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upversionlab.model.Company;
import com.upversionlab.model.Ingredient;
import com.upversionlab.model.Product;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vruzeda on 14/02/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IngredientController.class)
public class IngredientControllerTest {

    private static final Ingredient INGREDIENT = new Ingredient();
    private static final Integer INGREDIENT_ID = 1;
    private static final Boolean INGREDIENT_VEGAN = true;
    private static final Product INGREDIENT_PRODUCT = createProduct();

    private static final Ingredient OTHER_INGREDIENT = new Ingredient();
    private static final Integer OTHER_INGREDIENT_ID = 2;
    private static final Boolean OTHER_INGREDIENT_VEGAN = false;
    private static final Product OTHER_INGREDIENT_PRODUCT = createProduct();

    private static final Ingredient UPDATED_INGREDIENT = new Ingredient();
    private static final Boolean UPDATED_INGREDIENT_VEGAN = false;
    private static final Product UPDATED_INGREDIENT_PRODUCT = createProduct();

    private static Product createProduct() {
        Product product = new Product();
        product.setId(0);
        product.setCompany(createCompany());
        product.setIngredients(Lists.emptyList());
        return product;
    }

    private static Company createCompany() {
        Company company = new Company();
        company.setId(0);
        company.setVegan(true);
        company.setProducts(Lists.emptyList());
        return company;
    }

    @MockBean
    private Map<Integer, Ingredient> ingredients;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        INGREDIENT.setId(INGREDIENT_ID);
        INGREDIENT.setVegan(INGREDIENT_VEGAN);
        INGREDIENT.setProduct(INGREDIENT_PRODUCT);

        OTHER_INGREDIENT.setId(OTHER_INGREDIENT_ID);
        OTHER_INGREDIENT.setVegan(OTHER_INGREDIENT_VEGAN);
        OTHER_INGREDIENT.setProduct(OTHER_INGREDIENT_PRODUCT);

        UPDATED_INGREDIENT.setId(INGREDIENT_ID);
        UPDATED_INGREDIENT.setVegan(UPDATED_INGREDIENT_VEGAN);
        UPDATED_INGREDIENT.setProduct(UPDATED_INGREDIENT_PRODUCT);

        when(ingredients.get(INGREDIENT_ID)).thenReturn(INGREDIENT);
        when(ingredients.values()).thenReturn(Lists.newArrayList(INGREDIENT));
        when(ingredients.size()).thenReturn(OTHER_INGREDIENT_ID);
    }

    @Test
    public void testGetIngredients() throws Exception {
        mvc.perform(get("/ingredients").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(INGREDIENT_ID)))
                .andExpect(jsonPath("$[0].vegan", is(INGREDIENT_VEGAN)))
                .andExpect(jsonPath("$[0].product.id", is(INGREDIENT_PRODUCT.getId())))
                .andExpect(jsonPath("$[0].product.company.id", is(INGREDIENT_PRODUCT.getCompany().getId())))
                .andExpect(jsonPath("$[0].product.company.vegan", is(INGREDIENT_PRODUCT.getCompany().isVegan())))
                .andExpect(jsonPath("$[0].product.company.products.length()", is(INGREDIENT_PRODUCT.getCompany().getProducts().size())))
                .andExpect(jsonPath("$[0].product.ingredients.length()", is(INGREDIENT_PRODUCT.getIngredients().size())));

        verify(ingredients).values();
    }

    @Test
    public void testGetIngredient() throws Exception {
        mvc.perform(get("/ingredients/" + INGREDIENT_ID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(INGREDIENT_ID)))
                .andExpect(jsonPath("$.vegan", is(INGREDIENT_VEGAN)))
                .andExpect(jsonPath("$.product.id", is(INGREDIENT_PRODUCT.getId())))
                .andExpect(jsonPath("$.product.company.id", is(INGREDIENT_PRODUCT.getCompany().getId())))
                .andExpect(jsonPath("$.product.company.vegan", is(INGREDIENT_PRODUCT.getCompany().isVegan())))
                .andExpect(jsonPath("$.product.company.products.length()", is(INGREDIENT_PRODUCT.getCompany().getProducts().size())))
                .andExpect(jsonPath("$.product.ingredients.length()", is(INGREDIENT_PRODUCT.getIngredients().size())));

        verify(ingredients).get(INGREDIENT_ID);
    }

    @Test
    public void testCreateIngredient() throws Exception {
        mvc.perform(put("/ingredients").contentType(MediaType.APPLICATION_JSON).content(asJsonString(OTHER_INGREDIENT)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(OTHER_INGREDIENT_ID)))
                .andExpect(jsonPath("$.vegan", is(OTHER_INGREDIENT_VEGAN)))
                .andExpect(jsonPath("$.product.id", is(OTHER_INGREDIENT_PRODUCT.getId())))
                .andExpect(jsonPath("$.product.company.id", is(OTHER_INGREDIENT_PRODUCT.getCompany().getId())))
                .andExpect(jsonPath("$.product.company.vegan", is(OTHER_INGREDIENT_PRODUCT.getCompany().isVegan())))
                .andExpect(jsonPath("$.product.company.products.length()", is(OTHER_INGREDIENT_PRODUCT.getCompany().getProducts().size())))
                .andExpect(jsonPath("$.product.ingredients.length()", is(OTHER_INGREDIENT_PRODUCT.getIngredients().size())));

        verify(ingredients).put(OTHER_INGREDIENT_ID, OTHER_INGREDIENT);
    }

    @Test
    public void testUpdateIngredient() throws Exception {
        mvc.perform(post("/ingredients/" + INGREDIENT_ID).contentType(MediaType.APPLICATION_JSON).content(asJsonString(UPDATED_INGREDIENT)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(INGREDIENT_ID)))
                .andExpect(jsonPath("$.vegan", is(UPDATED_INGREDIENT_VEGAN)))
                .andExpect(jsonPath("$.product.id", is(UPDATED_INGREDIENT_PRODUCT.getId())))
                .andExpect(jsonPath("$.product.company.id", is(UPDATED_INGREDIENT_PRODUCT.getCompany().getId())))
                .andExpect(jsonPath("$.product.company.vegan", is(UPDATED_INGREDIENT_PRODUCT.getCompany().isVegan())))
                .andExpect(jsonPath("$.product.company.products.length()", is(UPDATED_INGREDIENT_PRODUCT.getCompany().getProducts().size())))
                .andExpect(jsonPath("$.product.ingredients.length()", is(UPDATED_INGREDIENT_PRODUCT.getIngredients().size())));

        verify(ingredients).get(INGREDIENT_ID);
        assertThat(INGREDIENT).isEqualTo(UPDATED_INGREDIENT);
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        mvc.perform(delete("/ingredients/" + INGREDIENT_ID).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(ingredients).remove(INGREDIENT);
    }

    private String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}