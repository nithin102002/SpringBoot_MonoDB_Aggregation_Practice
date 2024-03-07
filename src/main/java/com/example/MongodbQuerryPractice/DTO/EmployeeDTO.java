package com.example.MongodbQuerryPractice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private String name;
    private String firstName;
    private String lastName;
    private int age;
    private String department;
}
