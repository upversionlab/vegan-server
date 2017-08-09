package com.upversionlab.controller;

import com.upversionlab.exception.StorageFileNotFoundException;
import com.upversionlab.model.PendingProduct;
import com.upversionlab.repository.PendingProductRepository;
import com.upversionlab.storage.FileSystemStorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {

    private static final String UPLOAD_DATE = "UPLOAD_DATE";
    private static final Long ID = 1L;

    private MockMultipartFile barcodeFile;
    private MockMultipartFile logo;
    private MockMultipartFile ingredientList;
    private MockMultipartFile nutritionalFacts;

    @MockBean
    private FileSystemStorageService fileSystemStorageService;

    @MockBean
    private PendingProductRepository pendingProductRepository;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        barcodeFile = new MockMultipartFile("barcodeFile", "barcode.jpg", "image/jpeg", "".getBytes());
        logo = new MockMultipartFile("logo", "logo.jpg", "image/jpeg", "".getBytes());
        ingredientList = new MockMultipartFile("ingredientList", "ingredientList.jpg", "image/jpeg", "".getBytes());
        nutritionalFacts = new MockMultipartFile("nutritionalFacts", "nutritionalFacts.jpg", "image/jpeg", "".getBytes());
    }

    @Test
    public void testExistentUpdateIngredient() throws Exception {
        when(pendingProductRepository.findByIdAndUploadDate(ID, UPLOAD_DATE)).thenReturn(new PendingProduct());
        mvc.perform(fileUpload("/uploadAndroid/" + UPLOAD_DATE + "/" + ID)
                .file(barcodeFile)
                .file(logo)
                .file(ingredientList)
                .file(nutritionalFacts)
                .param("some-random", "4"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void testNonExistentUpdateIngredient() throws Exception {
        mvc.perform(fileUpload("/uploadAndroid/" + UPLOAD_DATE + "/" + ID)
                .file(barcodeFile)
                .file(logo)
                .file(ingredientList)
                .file(nutritionalFacts)
                .param("some-random", "4"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testUpdateIngredientException() throws Exception {
        when(pendingProductRepository.findByIdAndUploadDate(ID, UPLOAD_DATE)).thenReturn(new PendingProduct());
        doThrow(new StorageFileNotFoundException("Exception")).when(fileSystemStorageService).storeAndroid(UPLOAD_DATE, ID, barcodeFile, logo, ingredientList, nutritionalFacts);

        mvc.perform(fileUpload("/uploadAndroid/" + UPLOAD_DATE + "/" + ID)
                .file(barcodeFile)
                .file(logo)
                .file(ingredientList)
                .file(nutritionalFacts)
                .param("some-random", "4"))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @TestConfiguration
    public static class Configuration {

        @Bean
        public String storageLocation() { return "location"; };
    }

}
