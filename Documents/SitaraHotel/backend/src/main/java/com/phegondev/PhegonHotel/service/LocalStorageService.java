package com.phegondev.PhegonHotel.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LocalStorageService {
    private static final Logger logger = LoggerFactory.getLogger(LocalStorageService.class);
    private final String uploadDir = "uploads/";

    public String saveImageToLocal(MultipartFile photo) {
        try {
            Files.createDirectories(Paths.get(uploadDir));
            String filename = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            logger.info("Saving file to: {}", filePath.toAbsolutePath());
            photo.transferTo(filePath.toFile());
            logger.info("File saved successfully: {}", filePath.toAbsolutePath());
            return "/images/" + filename; // Adjust this path as per your static resource mapping
        } catch (IOException e) {
            logger.error("Failed to store file: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
        }
    }
}
