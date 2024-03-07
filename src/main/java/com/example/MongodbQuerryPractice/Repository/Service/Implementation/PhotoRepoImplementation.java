package com.example.MongodbQuerryPractice.Repository.Service.Implementation;

import com.example.MongodbQuerryPractice.Model.Photo;
import com.example.MongodbQuerryPractice.Repository.PhotoRepository;
import com.example.MongodbQuerryPractice.Repository.Service.PhotoRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoRepoImplementation implements PhotoRepoService {

    private final PhotoRepository photoRepository;

    public PhotoRepoImplementation(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public String save(Photo photo) {
        return photoRepository.save(photo).getPhotoId();
    }

    @Override
    public Photo findById(String id) {
        return photoRepository.findById(id).get();
    }
}
