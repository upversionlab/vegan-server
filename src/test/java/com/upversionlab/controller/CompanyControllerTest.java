package com.upversionlab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upversionlab.model.Company;
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
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    private static final Company COMPANY = new Company();
    private static final Integer COMPANY_ID = 1;
    private static final Boolean COMPANY_VEGAN = true;
    private static final List<Product> COMPANY_PRODUCTS = Lists.emptyList();

    private static final Company OTHER_COMPANY = new Company();
    private static final Integer OTHER_COMPANY_ID = 2;
    private static final Boolean OTHER_COMPANY_VEGAN = false;
    private static final List<Product> OTHER_COMPANY_PRODUCTS = Lists.emptyList();

    private static final Company UPDATED_COMPANY = new Company();
    private static final Boolean UPDATED_COMPANY_VEGAN = false;
    private static final List<Product> UPDATED_COMPANY_PRODUCTS = Lists.emptyList();

    @MockBean
    private Map<Integer, Company> companies;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        COMPANY.setId(COMPANY_ID);
        COMPANY.setVegan(COMPANY_VEGAN);
        COMPANY.setProducts(COMPANY_PRODUCTS);

        OTHER_COMPANY.setId(OTHER_COMPANY_ID);
        OTHER_COMPANY.setVegan(OTHER_COMPANY_VEGAN);
        OTHER_COMPANY.setProducts(OTHER_COMPANY_PRODUCTS);

        UPDATED_COMPANY.setId(COMPANY_ID);
        UPDATED_COMPANY.setVegan(UPDATED_COMPANY_VEGAN);
        UPDATED_COMPANY.setProducts(UPDATED_COMPANY_PRODUCTS);

        when(companies.get(COMPANY_ID)).thenReturn(COMPANY);
        when(companies.values()).thenReturn(Lists.newArrayList(COMPANY));
        when(companies.size()).thenReturn(OTHER_COMPANY_ID);
    }

    @Test
    public void testGetCompanies() throws Exception {
        mvc.perform(get("/companies").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(COMPANY_ID)))
                .andExpect(jsonPath("$[0].vegan", is(COMPANY_VEGAN)))
                .andExpect(jsonPath("$[0].products.length()", is(COMPANY_PRODUCTS.size())));

        verify(companies).values();
    }

    @Test
    public void testGetCompany() throws Exception {
        mvc.perform(get("/companies/" + COMPANY_ID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(COMPANY_ID)))
                .andExpect(jsonPath("$.vegan", is(COMPANY_VEGAN)))
                .andExpect(jsonPath("$.products.length()", is(COMPANY_PRODUCTS.size())));

        verify(companies).get(COMPANY_ID);
    }

    @Test
    public void testCreateCompany() throws Exception {
        mvc.perform(put("/companies").contentType(MediaType.APPLICATION_JSON).content(asJsonString(OTHER_COMPANY)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(OTHER_COMPANY_ID)))
                .andExpect(jsonPath("$.vegan", is(OTHER_COMPANY_VEGAN)))
                .andExpect(jsonPath("$.products.length()", is(OTHER_COMPANY_PRODUCTS.size())));

        verify(companies).put(OTHER_COMPANY_ID, OTHER_COMPANY);
    }

    @Test
    public void testUpdateCompany() throws Exception {
        mvc.perform(post("/companies/" + COMPANY_ID).contentType(MediaType.APPLICATION_JSON).content(asJsonString(UPDATED_COMPANY)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(COMPANY_ID)))
                .andExpect(jsonPath("$.vegan", is(UPDATED_COMPANY_VEGAN)))
                .andExpect(jsonPath("$.products.length()", is(UPDATED_COMPANY_PRODUCTS.size())));

        verify(companies).get(COMPANY_ID);
        assertThat(COMPANY).isEqualTo(UPDATED_COMPANY);
    }

    @Test
    public void testDeleteCompany() throws Exception {
        mvc.perform(delete("/companies/" + COMPANY_ID).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(companies).remove(COMPANY);
    }

    private String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}