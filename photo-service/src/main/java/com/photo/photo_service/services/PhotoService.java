package com.photo.photo_service.services;

import com.photo.photo_service.models.Photo;
import com.photo.photo_service.repositories.PhotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void createTestPhoto() {
        try {
            File file = new File("D:/Изображения/AI/Copilot_20251207_085957.png");
            byte[] photoBytes = Files.readAllBytes(file.toPath());
            log.info("Get test photo");
            photoRepository.insertPhoto("test_image", 50.0, 50.0, photoBytes);
            log.info("The test photo has been successfully added!");
        } catch (Exception e) {
            log.error("Cannot insert photo", e);
        }
    }

//    public Photo modifyPhoto(byte shift) {
//        return new Photo();
//    }

    public List<Photo> getAllPhotos() {
        return photoRepository.getAllPhotos();
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.getPhotoById(id);
    }

    public boolean deletePhoto(Long id) {
        return photoRepository.deletePhoto(id);
    }
}
