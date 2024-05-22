package com.henrik23mr.storageservice.service;

import com.henrik23mr.storageservice.entity.ImageData;
import com.henrik23mr.storageservice.repository.StorageRepository;
import com.henrik23mr.storageservice.utils.ImageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StorageServiceTest {

    @InjectMocks
    private StorageService storageService;

    @Mock
    private StorageRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadImageSuccess() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg",
                "image/jpeg", "test image content".getBytes());
        ImageData mockImageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imagesData(ImageUtils.compressImage(file.getBytes()))
                .build();

        when(repository.save(any(ImageData.class))).thenReturn(mockImageData);

        String result = storageService.uploadImage(file);
        assertEquals("Image uploaded successfully with test.jpg!", result);
    }

    @Test
    public void testUploadImageInvalidFileType() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt",
                "text/plain", "test content".getBytes());

        String result = storageService.uploadImage(file);
        assertEquals("Only PNG, JPG and JPEG files are allowed", result);
    }

    @Test
    public void testUploadImageSaveError() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg",
                "image/jpeg", "test image content".getBytes());

        when(repository.save(any(ImageData.class))).thenReturn(null);

        String result = storageService.uploadImage(file);
        assertEquals("Some error occurred", result);
    }
}