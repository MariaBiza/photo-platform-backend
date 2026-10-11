package com.photo.photo_service.services.interfaces;

import com.photo.photo_service.models.PhotoDTO;

import java.util.List;

public interface PhotoService {
    void createTestPhoto();
    List<PhotoDTO> getAllPhotos();
    PhotoDTO getPhotoById(Long id);
    boolean deletePhoto(Long id);
}
