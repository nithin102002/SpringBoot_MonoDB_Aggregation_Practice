package com.example.MongodbQuerryPractice.Repository.Service;

import com.example.MongodbQuerryPractice.Model.Photo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhotoRepoService {
    String save(Photo photo);

    Photo findById(String id);
}
