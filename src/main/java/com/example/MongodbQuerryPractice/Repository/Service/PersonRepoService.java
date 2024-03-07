package com.example.MongodbQuerryPractice.Repository.Service;

import com.example.MongodbQuerryPractice.DTO.PersonDTO;
import com.example.MongodbQuerryPractice.Model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonRepoService {


  Person save(Person person);

    List<Person> findByfirstName(String name);

  List<Person> findAll();

  void deleteById(String id);

  List<Person> findPersonByAgeBetween(int minAge, int maxAge);
}
