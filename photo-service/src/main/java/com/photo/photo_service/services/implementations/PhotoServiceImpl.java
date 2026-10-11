package com.photo.photo_service.services.implementations;

import com.photo.photo_service.mapper.PhotoMapper;
import com.photo.photo_service.models.PhotoDTO;
import com.photo.photo_service.repositories.interfaces.PhotoRepository;
import com.photo.photo_service.services.interfaces.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;

    @Override
    public void createTestPhoto() {
        try {
            File file = new File("D:/Изображения/AI/Copilot_20251207_085957.png");
            byte[] photoBytes = Files.readAllBytes(file.toPath());
            String pureBase64 = Base64.getEncoder().encodeToString(photoBytes);
            String base64Photo = "data:image/png;base64," + pureBase64;
            log.info("Get test photo");
            PhotoDTO photoDTO = new PhotoDTO("test_image", 50.0, 50.0, base64Photo);
            photoRepository.insertPhoto(photoDTO);
            log.info("The test photo has been successfully added!");
        } catch (Exception e) {
            log.error("Cannot insert photo", e);
        }
    }

    @Override
    public List<PhotoDTO> getAllPhotos() {
        return photoRepository.getAllPhotos().stream().map(photoMapper::toDto).toList();
    }

    @Override
    public PhotoDTO getPhotoById(Long id) {
        return photoMapper.toDto(photoRepository.getPhotoById(id));
    }

    @Override
    public boolean deletePhoto(Long id) {
        return photoRepository.deletePhoto(id);
    }
}
