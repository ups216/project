package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PhotosController {

    private Map<String, Photo> db = new HashMap<>(){
        {
            put("1", new Photo("1", "photo1.jpg"));
            put("2", new Photo("2", "photo2.jpg"));
            put("3", new Photo("3", "photo3.jpg"));
        }
    };
    
    @GetMapping("/")
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("/photos")
    public List<Photo> getPhotos(){
        return new ArrayList<>(db.values());
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable String id){
        Photo photo = db.get(id);
        if(photo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return db.get(id);
    }

    // 按id删除照片
    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable String id){
        Photo photo = db.get(id);
        if(photo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        db.remove(id);
    }
}
