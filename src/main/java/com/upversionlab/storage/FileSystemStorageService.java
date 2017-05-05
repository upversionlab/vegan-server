package com.upversionlab.storage;

import com.upversionlab.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class FileSystemStorageService {

    private final String storageLocation;

    @Autowired
    public FileSystemStorageService(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public void storeAndroid(MultipartFile barcodeFile, MultipartFile logo, MultipartFile ingredientList, MultipartFile nutritionalFacts) {
        Path rootLocation = createFolders();
        doStore(rootLocation, barcodeFile, "barcode.jpg");
        doStore(rootLocation, logo, "logo.jpg");
        doStore(rootLocation, ingredientList, "ingredientList.jpg");
        doStore(rootLocation, nutritionalFacts, "nutrictionalFacts.jpg");
    }

    private Path createFolders() {
        Long now = Calendar.getInstance().getTimeInMillis();
        Path rootLocation = Paths.get(storageLocation, now.toString());
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not create the storage: " + storageLocation + "/" + now.toString(), e);
        }
        return rootLocation;
    }

    private void doStore(Path rootLocation, MultipartFile file, String filename) {
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
