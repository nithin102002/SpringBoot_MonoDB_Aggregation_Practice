package com.example.MongodbQuerryPractice.Repository.Service.Implementation;

import com.example.MongodbQuerryPractice.DTO.PersonDTO;
import com.example.MongodbQuerryPractice.Model.Person;
import com.example.MongodbQuerryPractice.Repository.PersonRepository;
import com.example.MongodbQuerryPractice.Repository.Service.PersonRepoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonRepoServiceImplementation implements PersonRepoService {

    public PersonRepoServiceImplementation(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final PersonRepository personRepository;
    @Override
    public Person save(Person person) {
        return personRepository.save(person) ;
    }

    @Override
    public List<Person> findByfirstName(String name) {
        return personRepository.findByfirstName(name);

    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
         personRepository.deleteById(id);
    }

    @Override
    public List<Person> findPersonByAgeBetween(int minAge, int maxAge) {
        return personRepository.findPersonByAgeBetween(minAge,maxAge);
    }
}
