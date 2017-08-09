package com.upversionlab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.upversionlab.model.PendingProduct;
import com.upversionlab.repository.PendingProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PendingProductController.class)
public class PendingProductControllerTest {

    private static final Long PENDING_PRODUCT_ID = 1L;
    private static final String PENDING_PRODUCT_UPLOAD_DATE = "0";
    private static final String PENDING_PRODUCT_BARCODE = "PENDING_PRODUCT_BARCODE";
    private static final String PENDING_PRODUCT_INGREDIENTS = "PENDING_PRODUCT_INGREDIENTS";
    private static final String PENDING_PRODUCT_NUTRITIONAL_FACTS = "PENDING_PRODUCT_NUTRITIONAL_FACTS";

    private final PendingProduct pendingProduct = createPendingProduct();

    @MockBean
    private PendingProductRepository pendingProductRepository;

    @MockBean
    private Calendar calendar;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        when(pendingProductRepository.findAll()).thenReturn(Collections.singletonList(pendingProduct));
        when(pendingProductRepository.findByIdAndUploadDate(PENDING_PRODUCT_ID, PENDING_PRODUCT_UPLOAD_DATE)).thenReturn(pendingProduct);
        when(pendingProductRepository.save(pendingProduct)).thenReturn(pendingProduct);

        when(calendar.getTimeInMillis()).thenReturn(Long.parseLong(PENDING_PRODUCT_UPLOAD_DATE));
    }

    @Test
    public void testGetAllPendingProduct() throws Exception {
        mvc.perform(get("/pendingProducts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" + getPendingProductAsJson() + "]"));
    }

    @Test
    public void testGetOnePendingProduct() throws Exception {
        mvc.perform(get("/pendingProducts/" + PENDING_PRODUCT_UPLOAD_DATE + "/" + PENDING_PRODUCT_ID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getPendingProductAsJson()));
    }

    @Test
    public void testCreatePendingProduct() throws Exception {
        mvc.perform(post("/pendingProducts").accept(MediaType.APPLICATION_JSON)
                .content(getPendingProductAsJson()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getPendingProductAsJson()));
    }

    @Test
    public void testDeletePendingProduct() throws Exception {
        mvc.perform(delete("/pendingProducts/" + PENDING_PRODUCT_UPLOAD_DATE + "/" + PENDING_PRODUCT_ID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private PendingProduct createPendingProduct() {
        PendingProduct pendingProduct = new PendingProduct();
        pendingProduct.setId(PENDING_PRODUCT_ID);
        pendingProduct.setUploadDate(PENDING_PRODUCT_UPLOAD_DATE);
        pendingProduct.setBarcode(PENDING_PRODUCT_BARCODE);
        pendingProduct.setIngredients(PENDING_PRODUCT_INGREDIENTS);
        pendingProduct.setNutritionalFacts(PENDING_PRODUCT_NUTRITIONAL_FACTS);
        return pendingProduct;
    }

    private String getPendingProductAsJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(pendingProduct);
    }

}
