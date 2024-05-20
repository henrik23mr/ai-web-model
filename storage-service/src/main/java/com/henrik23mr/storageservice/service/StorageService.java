package com.henrik23mr.storageservice.service;

import ch.qos.logback.core.status.Status;
import com.henrik23mr.storageservice.entity.ImageData;
import com.henrik23mr.storageservice.repository.StorageRepository;
import com.henrik23mr.storageservice.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imagesData(ImageUtils.compressImage(file.getBytes())).build());

        if (imageData != null){
            return "Image uploaded successfully with " + file.getName() + "!";
        }
        return "Some error occoured";

    }


}
