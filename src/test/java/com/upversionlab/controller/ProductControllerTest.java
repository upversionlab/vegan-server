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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private static final Product PRODUCT = new Product();
    private static final Integer PRODUCT_ID = 1;
    private static final Company PRODUCT_COMPANY = createCompany();
    private static final List<Ingredient> PRODUCT_INGREDIENTS = Lists.emptyList();

    private static final Product OTHER_PRODUCT = new Product();
    private static final Integer OTHER_PRODUCT_ID = 2;
    private static final Company OTHER_PRODUCT_COMPANY = createCompany();
    private static final List<Ingredient> OTHER_PRODUCT_INGREDIENTS = Lists.emptyList();

    private static final Product UPDATED_PRODUCT = new Product();
    private static final Company UPDATED_PRODUCT_COMPANY = createCompany();
    private static final List<Ingredient> UPDATED_PRODUCT_INGREDIENTS = Lists.emptyList();

    private static Company createCompany() {
        Company company = new Company();
        company.setId(0);
        company.setVegan(true);
        company.setProducts(Lists.emptyList());
        return company;
    }

    @MockBean
    private Map<Integer, Product> products;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setCompany(PRODUCT_COMPANY);
        PRODUCT.setIngredients(PRODUCT_INGREDIENTS);

        OTHER_PRODUCT.setId(OTHER_PRODUCT_ID);
        OTHER_PRODUCT.setCompany(OTHER_PRODUCT_COMPANY);
        OTHER_PRODUCT.setIngredients(OTHER_PRODUCT_INGREDIENTS);

        UPDATED_PRODUCT.setId(PRODUCT_ID);
        UPDATED_PRODUCT.setCompany(UPDATED_PRODUCT_COMPANY);
        UPDATED_PRODUCT.setIngredients(UPDATED_PRODUCT_INGREDIENTS);

        when(products.get(PRODUCT_ID)).thenReturn(PRODUCT);
        when(products.values()).thenReturn(Lists.newArrayList(PRODUCT));
        when(products.size()).thenReturn(OTHER_PRODUCT_ID);
    }

    @Test
    public void testGetProducts() throws Exception {
        mvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(PRODUCT_ID)))
                .andExpect(jsonPath("$[0].company.id", is(PRODUCT_COMPANY.getId())))
                .andExpect(jsonPath("$[0].company.vegan", is(PRODUCT_COMPANY.isVegan())))
                .andExpect(jsonPath("$[0].company.products.length()", is(PRODUCT_COMPANY.getProducts().size())))
                .andExpect(jsonPath("$[0].ingredients.length()", is(PRODUCT_INGREDIENTS.size())));

        verify(products).values();
    }

    @Test
    public void testGetProduct() throws Exception {
        mvc.perform(get("/products/" + PRODUCT_ID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(PRODUCT_ID)))
                .andExpect(jsonPath("$.company.id", is(PRODUCT_COMPANY.getId())))
                .andExpect(jsonPath("$.company.vegan", is(PRODUCT_COMPANY.isVegan())))
                .andExpect(jsonPath("$.company.products.length()", is(PRODUCT_COMPANY.getProducts().size())))
                .andExpect(jsonPath("$.ingredients.length()", is(PRODUCT_INGREDIENTS.size())));

        verify(products).get(PRODUCT_ID);
    }

    @Test
    public void testCreateProduct() throws Exception {
        mvc.perform(put("/products").contentType(MediaType.APPLICATION_JSON).content(asJsonString(OTHER_PRODUCT)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(OTHER_PRODUCT_ID)))
                .andExpect(jsonPath("$.company.id", is(OTHER_PRODUCT_COMPANY.getId())))
                .andExpect(jsonPath("$.company.vegan", is(OTHER_PRODUCT_COMPANY.isVegan())))
                .andExpect(jsonPath("$.company.products.length()", is(OTHER_PRODUCT_COMPANY.getProducts().size())))
                .andExpect(jsonPath("$.ingredients.length()", is(OTHER_PRODUCT_INGREDIENTS.size())));

        verify(products).put(OTHER_PRODUCT_ID, OTHER_PRODUCT);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        mvc.perform(post("/products/" + PRODUCT_ID).contentType(MediaType.APPLICATION_JSON).content(asJsonString(UPDATED_PRODUCT)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(PRODUCT_ID)))
                .andExpect(jsonPath("$.company.id", is(UPDATED_PRODUCT_COMPANY.getId())))
                .andExpect(jsonPath("$.company.vegan", is(UPDATED_PRODUCT_COMPANY.isVegan())))
                .andExpect(jsonPath("$.company.products.length()", is(UPDATED_PRODUCT_COMPANY.getProducts().size())))
                .andExpect(jsonPath("$.ingredients.length()", is(OTHER_PRODUCT_INGREDIENTS.size())));

        verify(products).get(PRODUCT_ID);
        assertThat(PRODUCT).isEqualTo(UPDATED_PRODUCT);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mvc.perform(delete("/products/" + PRODUCT_ID).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(products).remove(PRODUCT);
    }

    private String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}