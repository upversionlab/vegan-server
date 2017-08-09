package com.upversionlab.controller;

import com.upversionlab.exception.StorageFileNotFoundException;
import com.upversionlab.model.PendingProduct;
import com.upversionlab.repository.PendingProductRepository;
import com.upversionlab.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    private final FileSystemStorageService fileSystemStorageService;
    private final PendingProductRepository pendingProductRepository;

    @Autowired
    public FileUploadController(FileSystemStorageService fileSystemStorageService, PendingProductRepository pendingProductRepository) {
        this.fileSystemStorageService = fileSystemStorageService;
        this.pendingProductRepository = pendingProductRepository;
    }

    @PostMapping("/uploadAndroid/{uploadDate}/{id}")
    public ResponseEntity handleFileUploadAndroid(@PathVariable String uploadDate,
            @PathVariable Long id,
            @RequestParam MultipartFile barcodeFile,
            @RequestParam MultipartFile logo,
            @RequestParam MultipartFile ingredientList,
            @RequestParam MultipartFile nutritionalFacts) {

        PendingProduct pendingProduct = pendingProductRepository.findByIdAndUploadDate(id, uploadDate);
        if (pendingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        fileSystemStorageService.storeAndroid(uploadDate, id, barcodeFile, logo, ingredientList, nutritionalFacts);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
