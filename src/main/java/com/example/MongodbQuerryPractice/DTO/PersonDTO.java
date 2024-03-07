package com.example.MongodbQuerryPractice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {


    private String firstName;
    private String lastName;
    private int age;
    private List<String> hobbies;
    private List<Address> addresses;
}
