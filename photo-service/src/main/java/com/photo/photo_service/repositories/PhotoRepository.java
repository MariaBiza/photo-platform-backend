package com.photo.photo_service.repositories;

import com.photo.photo_service.models.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class PhotoRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Photo> PHOTO_ROW_MAPPER = (r, i) -> {
        Photo p = new Photo();
        p.setId(r.getLong("id"));
        p.setName(r.getString("name"));
        p.setHeight(r.getDouble("height"));
        p.setWidth(r.getDouble("width"));
        p.setPhoto(r.getBytes("photo"));
        return p;
    };

    public PhotoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Photo> getAllPhotos(){
        log.info("START getAllPhotos");
        String sql = "SELECT * FROM photo";
        return jdbcTemplate.query(sql, PHOTO_ROW_MAPPER);
    }

    public Photo getPhotoById(Long id) {
        log.info("START getPhotoById");
        return jdbcTemplate.queryForObject("SELECT * FROM photo WHERE id=?", PHOTO_ROW_MAPPER, id);
    }

    public void insertPhoto(String name, double height, double width, byte[] photoBytes) {
        log.info("START insertPhoto");
        String sql = "INSERT INTO photo (name, height, width, photo) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, name, height, width, photoBytes);
    }
}
