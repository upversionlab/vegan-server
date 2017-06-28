package com.upversionlab.storage;

import com.upversionlab.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@Service
public class FileSystemStorageService {
    static final String BARCODE_FILENAME = "barcode.jpg";
    static final String LOGO_FILENAME = "logo.jpg";
    static final String INGREDIENT_LIST_FILENAME = "ingredientList.jpg";
    static final String NUTRICTIONAL_FACTS_FILENAME = "nutrictionalFacts.jpg";

    private final String storageLocation;
    private final Calendar calendar;

    @Autowired
    public FileSystemStorageService(String storageLocation, Calendar calendar) {
        this.storageLocation = storageLocation;
        this.calendar = calendar;
    }

    public void storeAndroid(MultipartFile barcodeFile, MultipartFile logo, MultipartFile ingredientList, MultipartFile nutritionalFacts) {
        Path rootLocation = createFolders();
        doStore(rootLocation, barcodeFile, BARCODE_FILENAME);
        doStore(rootLocation, logo, LOGO_FILENAME);
        doStore(rootLocation, ingredientList, INGREDIENT_LIST_FILENAME);
        doStore(rootLocation, nutritionalFacts, NUTRICTIONAL_FACTS_FILENAME);
    }

    Path createFolders() {
        Long now = calendar.getTimeInMillis();
        Path rootLocation = Paths.get(storageLocation, now.toString());
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not create the storage: " + storageLocation + "/" + now.toString(), e);
        }
        return rootLocation;
    }

    void doStore(Path rootLocation, MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), rootLocation.resolve(filename));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
