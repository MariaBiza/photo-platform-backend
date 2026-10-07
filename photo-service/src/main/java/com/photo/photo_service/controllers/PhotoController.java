package com.photo.photo_service.controllers;

import com.photo.photo_service.models.Photo;
import com.photo.photo_service.models.PhotoDTO;
import com.photo.photo_service.services.PhotoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<List<PhotoDTO>> getAllPhotos() {
        List<PhotoDTO> dtos = photoService.getAllPhotos().stream()
                .map(p -> new PhotoDTO(p.getId(), p.getName(), p.getHeight(), p.getWidth()))
                .toList();
        return ResponseEntity.ok(dtos);
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

    @DeleteMapping("/photo/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable("id") Long id) {
        if (photoService.deletePhoto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
