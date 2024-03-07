package com.example.MongodbQuerryPractice.API;

import com.example.MongodbQuerryPractice.DTO.EmployeeDTO;
import com.example.MongodbQuerryPractice.DTO.PersonDTO;
import com.example.MongodbQuerryPractice.DTO.ResponseDTO;
import com.example.MongodbQuerryPractice.Model.Person;
import com.example.MongodbQuerryPractice.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RequestMapping(value = "${Person}")
public interface PersonAPI {

     @PostMapping(value = "${AddingPerson}")
    public ResponseEntity<ResponseDTO> save(@RequestBody PersonDTO personDTO);

     @GetMapping(value = "${GettingPersonDetails}")
     public ResponseEntity<ResponseDTO> getAllPerson();

     @GetMapping(value = "${GettingPersonDetailsByFirstName}")
    public ResponseEntity<ResponseDTO> getPersonStartWith(@RequestParam String name);

     @DeleteMapping(value = "${DeltingPersonDetails}")
    public ResponseEntity<ResponseDTO> deletePersonDetails(@RequestParam String id);

     @GetMapping(value = "${GettingDetailsByage}")
    public ResponseEntity<ResponseDTO> gettingPersonDetailByAge(@RequestParam int minAge,int maxAge);

     @GetMapping(value = "${Search}")
    public ResponseEntity<ResponseDTO> SearchPerson(@RequestParam(required = false) String firstName,
                                                    @RequestParam(required = false) String lastName,
                                                    @RequestParam(required = false) String minAge,
                                                    @RequestParam(required = false) String maxAge,
                                                    @RequestParam(required = false) String city,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size

     );

    @GetMapping(value = "${GetOldestPerson}")
    public ResponseEntity<ResponseDTO> getOldestPerson();

    @GetMapping(value="${GetPopulationInEachCity}")
    public ResponseEntity<ResponseDTO> getPopulation();




}
