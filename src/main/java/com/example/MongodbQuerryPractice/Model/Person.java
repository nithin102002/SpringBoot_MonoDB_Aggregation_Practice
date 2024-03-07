package com.example.MongodbQuerryPractice.Model;

import com.example.MongodbQuerryPractice.DTO.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Id
    private String personId;
    private String firstName;
    private String lastName;
    private int age;
    private List<String> hobbies;
    private List<Address> addresses;
}
