package com.photo.photo_service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    private Long id;
    private String name;
    private double height;
    private double width;
    private byte[] photo;
}
