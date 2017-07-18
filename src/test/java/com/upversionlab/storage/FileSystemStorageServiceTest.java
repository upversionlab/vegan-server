package com.upversionlab.storage;

import com.upversionlab.exception.StorageException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileSystemStorageService.class)
public class FileSystemStorageServiceTest {
    private static final String STORAGE_LOCATION = "storage_location";
    private static final String ORIGINAL_FILENAME = "original_filename";
    private static final String FILENAME = "filename";
    private static final String UPLOAD_DATE = "UPLOAD_DATE";
    private static final Long ID = 1L;
    private static final String FOLDER_NAME = UPLOAD_DATE + "_" + ID;

    private FileSystemStorageService fileSystemStorageService;
    private Path rootLocation;
    private Path resolvedPath;
    private InputStream inputStream;
    private MultipartFile multiPartFile;

    @Before
    public void setUp() throws IOException {
        rootLocation = mock(Path.class);
        resolvedPath = mock(Path.class);
        when(rootLocation.resolve(FILENAME)).thenReturn(resolvedPath);
        when(rootLocation.resolve(FileSystemStorageService.BARCODE_FILENAME)).thenReturn(resolvedPath);
        when(rootLocation.resolve(FileSystemStorageService.LOGO_FILENAME)).thenReturn(resolvedPath);
        when(rootLocation.resolve(FileSystemStorageService.INGREDIENT_LIST_FILENAME)).thenReturn(resolvedPath);
        when(rootLocation.resolve(FileSystemStorageService.NUTRICTIONAL_FACTS_FILENAME)).thenReturn(resolvedPath);

        PowerMockito.mockStatic(Paths.class);
        when(Paths.get(STORAGE_LOCATION, FOLDER_NAME)).thenReturn(rootLocation);

        multiPartFile = mock(MultipartFile.class);
        when(multiPartFile.isEmpty()).thenReturn(false);
        when(multiPartFile.getOriginalFilename()).thenReturn(ORIGINAL_FILENAME);

        inputStream = mock(InputStream.class);
        when(multiPartFile.getInputStream()).thenReturn(inputStream);

        PowerMockito.mockStatic(Files.class);
        when(Files.createDirectories(rootLocation)).thenReturn(null);
        when(Files.copy(inputStream, resolvedPath)).thenReturn(0L);

        fileSystemStorageService = new FileSystemStorageService(STORAGE_LOCATION);
    }

    @Test
    public void testStoreAndroid() throws IOException {
        fileSystemStorageService.storeAndroid(UPLOAD_DATE, ID, multiPartFile, multiPartFile, multiPartFile, multiPartFile);

        verify(rootLocation).resolve(FileSystemStorageService.BARCODE_FILENAME);
        verify(rootLocation).resolve(FileSystemStorageService.LOGO_FILENAME);
        verify(rootLocation).resolve(FileSystemStorageService.INGREDIENT_LIST_FILENAME);
        verify(rootLocation).resolve(FileSystemStorageService.NUTRICTIONAL_FACTS_FILENAME);

        PowerMockito.verifyStatic(times(4));
        Files.copy(inputStream, resolvedPath);
    }

    @Test
    public void testCreateFolder() throws IOException {
        Path result = fileSystemStorageService.createFolders(UPLOAD_DATE, ID);

        PowerMockito.verifyStatic();
        Paths.get(STORAGE_LOCATION, FOLDER_NAME);

        PowerMockito.verifyStatic();
        Files.createDirectories(rootLocation);

        assertThat(result).isEqualTo(rootLocation);
    }

    @Test(expected = StorageException.class)
    public void testCreateFolderWithIOException() throws IOException {
        when(Files.createDirectories(rootLocation)).thenThrow(new IOException());

        fileSystemStorageService.createFolders(UPLOAD_DATE, ID);
    }

    @Test
    public void testDoStoreWithNonEmptyFile() throws IOException {
        fileSystemStorageService.doStore(rootLocation, multiPartFile, FILENAME);

        PowerMockito.verifyStatic();
        Files.copy(inputStream, resolvedPath);
    }

    @Test(expected = StorageException.class)
    public void testDoStoreWithEmptyFile() {
        when(multiPartFile.isEmpty()).thenReturn(true);

        fileSystemStorageService.doStore(rootLocation, multiPartFile, FILENAME);
    }

    @Test(expected = StorageException.class)
    public void testDoStoreWithIOException() throws IOException {
        when(Files.copy(inputStream, resolvedPath)).thenThrow(new IOException());

        fileSystemStorageService.doStore(rootLocation, multiPartFile, FILENAME);
    }
}
