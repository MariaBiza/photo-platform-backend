package com.photo.photo_service.mapper;

import com.photo.photo_service.models.Photo;
import com.photo.photo_service.models.PhotoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", source = "base64Photo", qualifiedByName = "base64ToBytes")
    Photo toEntity(PhotoDTO photoDTO);

    @Mapping(target = "base64Photo", source = "photo", qualifiedByName = "bytesToBase64")
    PhotoDTO toDto(Photo photo);

    @Named("bytesToBase64")
    default String bytesToBase64(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return "data:image/jpeg;base64," + base64;
    }

    @Named("base64ToBytes")
    default byte[] base64ToBytes(String base64WithPrefix) {
        if (base64WithPrefix == null || base64WithPrefix.isEmpty()) {
            return null;
        }
        String pureBase64 = base64WithPrefix.substring(base64WithPrefix.indexOf(",") + 1);
        return Base64.getDecoder().decode(pureBase64);
    }
}
