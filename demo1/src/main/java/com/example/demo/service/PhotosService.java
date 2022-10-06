package com.example.demo.service;

import com.example.demo.model.Photo;
import com.example.demo.repository.PhotosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotosService {
    private final PhotosRepository photosRepository;

    public Map<Integer, Photo> photoMap = new HashMap<>(){{
        put(1,new Photo(1,"Hello World!"));
    }};

    public PhotosService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    public Iterable<Photo> get() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public Photo save(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        //photo.setID(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        photo.setContentType(file.getContentType());
        photosRepository.save(photo);
        return photo;
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);
    }
}
