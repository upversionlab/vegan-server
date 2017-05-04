package com.upversionlab.storage;

import com.upversionlab.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(String storageLocation) {
        this.rootLocation = Paths.get(storageLocation);
    }

    public void storeAndroid(MultipartFile barcodeFile, MultipartFile logo, MultipartFile ingredientList, MultipartFile nutritionalFacts) {
        doStore(barcodeFile, "barcode.jpg");
        doStore(logo, "logo.jpg");
        doStore(ingredientList, "ingredientList.jpg");
        doStore(nutritionalFacts, "nutrictionalFacts.jpg");
    }

    private void doStore(MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
