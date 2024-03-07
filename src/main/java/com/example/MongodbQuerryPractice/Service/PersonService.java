package com.example.MongodbQuerryPractice.Service;

import com.example.MongodbQuerryPractice.DTO.AggregationResultDTO;
import com.example.MongodbQuerryPractice.DTO.EmployeeDTO;
import com.example.MongodbQuerryPractice.DTO.PersonDTO;
import com.example.MongodbQuerryPractice.DTO.ResponseDTO;
import com.example.MongodbQuerryPractice.Model.Person;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {


   public Person save(PersonDTO person);

    List<Person> getPersonStartWith(String name);

    List<Person> getAllPerson();


    String deletePersonDetails(String id);


    List<Person> getPersonByAge(int minAge, int maxAge);


 Page<Person> search(String firstName, String lastName, String minAge, String maxAge, String city, Pageable pageable);

 List<Document> getOldestPersonByCity();

 List<Document> getPopulationByCity();
}
