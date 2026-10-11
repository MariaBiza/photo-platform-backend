package com.photo.photo_service.repositories.interfaces;

import com.photo.photo_service.models.Photo;
import com.photo.photo_service.models.PhotoDTO;

import java.util.List;

public interface PhotoRepository {
    List<Photo> getAllPhotos();
    Photo getPhotoById(Long id);
    void insertPhoto(PhotoDTO photoDTO);
    boolean deletePhoto(Long id);
}
