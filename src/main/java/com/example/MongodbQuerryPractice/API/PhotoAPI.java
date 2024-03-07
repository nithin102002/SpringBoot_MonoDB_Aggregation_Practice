package com.example.MongodbQuerryPractice.API;

import com.example.MongodbQuerryPractice.DTO.ResponseDTO;
import com.example.MongodbQuerryPractice.Service.PhotoService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(value = "${Photo}")
public interface PhotoAPI {

    @PostMapping(value = "${AddingPhoto}")
    public ResponseEntity<ResponseDTO> addPhoto(@RequestParam MultipartFile image) throws IOException;

    @GetMapping(value = "${GetPhotoById}")
    public ResponseEntity<Resource> getPhotoById(@RequestParam String id);
}
