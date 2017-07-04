package com.upversionlab.controller;

import com.upversionlab.exception.StorageFileNotFoundException;
import com.upversionlab.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    private final FileSystemStorageService fileSystemStorageService;

    @Autowired
    public FileUploadController(FileSystemStorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @PostMapping("/uploadAndroid")
    public ResponseEntity handleFileUploadAndroid(@RequestParam MultipartFile barcodeFile,
                                                  @RequestParam MultipartFile logo,
                                                  @RequestParam MultipartFile ingredientList,
                                                  @RequestParam MultipartFile nutritionalFacts) {

        fileSystemStorageService.storeAndroid(barcodeFile, logo, ingredientList, nutritionalFacts);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
