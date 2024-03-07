package com.example.MongodbQuerryPractice.DTO;

import com.example.MongodbQuerryPractice.Model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregationResultDTO {

    @JsonProperty("maxAge")
    private Integer maxAge;
    private PersonDTO person;
}
