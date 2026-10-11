package com.photo.photo_service.controllers;

import com.photo.photo_service.models.PhotoDTO;
import com.photo.photo_service.services.interfaces.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllPhotos() {
        return ResponseEntity.ok(photoService.getAllPhotos().stream().map(PhotoDTO::name).toList());
    }

    @GetMapping(value = "/photo/{id}")
    public String getPhotoById(
            @PathVariable("id") Long id,
            Model model) {
        PhotoDTO photo = photoService.getPhotoById(id);
        model.addAttribute("photo", photo.base64Photo());
        return "one_photo.html";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("color", "green");
        return "home.html";
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addPhoto() {
        photoService.createTestPhoto();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/photo/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable("id") Long id) {
        if (photoService.deletePhoto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("catalog")
    public String catalog(Model model) {
        List<PhotoDTO> photos = photoService.getAllPhotos();
        model.addAttribute("photos", photos);
        return "catalog.html";
    }
}
