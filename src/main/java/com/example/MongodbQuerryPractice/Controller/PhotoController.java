package com.example.MongodbQuerryPractice.Controller;

import com.example.MongodbQuerryPractice.API.PhotoAPI;
import com.example.MongodbQuerryPractice.DTO.ResponseDTO;
import com.example.MongodbQuerryPractice.Model.Photo;
import com.example.MongodbQuerryPractice.Service.PhotoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController implements PhotoAPI {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Override
    public ResponseEntity<ResponseDTO> addPhoto(MultipartFile image) throws IOException {
        String id=photoService.addPhoto(image.getOriginalFilename(),image);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"addding Photo in Database",id));

    }

    @Override
    public ResponseEntity<Resource> getPhotoById(String id) {
        Photo photo= photoService.getPhoto(id);
        Resource resource= new ByteArrayResource(photo.getPhoto().getData());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;fileName=\"" + photo.getTitle()+ "\"").contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }
}
