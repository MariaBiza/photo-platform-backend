package com.photo.photo_service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Photo {
    private Long id;
    private String name;
    private double height;
    private double width;
    private byte[] photo;
}
