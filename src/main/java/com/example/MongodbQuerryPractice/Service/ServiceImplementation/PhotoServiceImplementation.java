package com.example.MongodbQuerryPractice.Service.ServiceImplementation;

import com.example.MongodbQuerryPractice.Model.Photo;
import com.example.MongodbQuerryPractice.Repository.Service.PhotoRepoService;
import com.example.MongodbQuerryPractice.Service.PhotoService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoServiceImplementation implements PhotoService {

    private final PhotoRepoService photoRepoService;

    public PhotoServiceImplementation(PhotoRepoService photoRepoService) {
        this.photoRepoService = photoRepoService;

    }

    @Override
    public String addPhoto(String originalFilename, MultipartFile image) throws IOException {

        Photo photo= new Photo();
        photo.setTitle(image.getOriginalFilename());
        photo.setPhoto(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
        return photoRepoService.save(photo);
    }

    @Override
    public Photo getPhoto(String id) {
        return photoRepoService.findById(id);
    }
}
