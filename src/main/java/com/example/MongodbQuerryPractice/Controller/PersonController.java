package com.example.MongodbQuerryPractice.Controller;

import com.example.MongodbQuerryPractice.API.PersonAPI;
import com.example.MongodbQuerryPractice.DTO.AggregationResultDTO;
import com.example.MongodbQuerryPractice.DTO.PersonDTO;
import com.example.MongodbQuerryPractice.DTO.ResponseDTO;
import com.example.MongodbQuerryPractice.Model.Person;
import com.example.MongodbQuerryPractice.Service.PersonService;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController implements PersonAPI {
  private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public ResponseEntity<ResponseDTO> save(PersonDTO person) {
        Person personData= personService.save(person);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Adding Persons Details",personData));
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllPerson() {
        List<Person> personList= personService.getAllPerson();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Getting All Person Details",personList));
    }

    @Override
    public ResponseEntity<ResponseDTO> getPersonStartWith(String name) {
        List<Person> personList= personService.getPersonStartWith(name);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Getting Person Details using First Name",personList));

    }

    @Override
    public ResponseEntity<ResponseDTO> deletePersonDetails(String id) {
        String response= personService.deletePersonDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Deleting Person Details",response));
    }

    @Override
    public ResponseEntity<ResponseDTO> gettingPersonDetailByAge(int minAge, int maxAge) {
        List<Person> personList= personService.getPersonByAge(minAge,maxAge);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Getting Person Details By Age",personList));
    }

    @Override
    public ResponseEntity<ResponseDTO> SearchPerson(String firstName, String lastName, String minAge, String maxAge, String city, int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Person> personPage=personService.search(firstName,lastName,minAge,maxAge,city,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Search Person using fields",personPage));
    }

    @Override
    public ResponseEntity<ResponseDTO> getOldestPerson() {
        List<Document> person= personService.getOldestPersonByCity();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Oldest Person",person));
    }

    @Override
    public ResponseEntity<ResponseDTO> getPopulation() {
        List<Document> person= personService.getPopulationByCity();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Population Count",person));

    }


}



