package com.photo.photo_service.controllers;

import com.photo.photo_service.models.Photo;
import com.photo.photo_service.services.PhotoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        return ResponseEntity
                .ok()
                .body(photoService.getAllPhotos());
    }

    @GetMapping(value = "/photo/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getPhotoById(
            @PathVariable("id") Long id) {
        Photo photo = photoService.getPhotoById(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(photo.getPhoto());
    }

    @GetMapping("/home")
    public String home(Model model) {
        photoService.createTestPhoto();
        model.addAttribute("color", "green");
        return "home.html";
    }
}
