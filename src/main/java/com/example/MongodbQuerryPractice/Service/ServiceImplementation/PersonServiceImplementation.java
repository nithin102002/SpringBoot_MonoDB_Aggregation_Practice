package com.example.MongodbQuerryPractice.Service.ServiceImplementation;

import com.example.MongodbQuerryPractice.Config.ModelMapperConfig;
import com.example.MongodbQuerryPractice.DTO.AggregationResultDTO;
import com.example.MongodbQuerryPractice.DTO.EmployeeDTO;
import com.example.MongodbQuerryPractice.DTO.PersonDTO;
import com.example.MongodbQuerryPractice.DTO.ResponseDTO;
import com.example.MongodbQuerryPractice.Model.Person;
import com.example.MongodbQuerryPractice.Repository.PersonRepository;
import com.example.MongodbQuerryPractice.Repository.Service.PersonRepoService;
import com.example.MongodbQuerryPractice.Service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PersonServiceImplementation implements PersonService {

private final PersonRepoService personRepoService;
private final ModelMapper modelMapper;
private final MongoTemplate mongoTemplate;

    public PersonServiceImplementation(PersonRepoService personRepoService, ModelMapper modelMapper, MongoTemplate mongoTemplate) {
        this.personRepoService = personRepoService;
        this.modelMapper = modelMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Person save(PersonDTO person) {
        Person personData= modelMapper.map(person,Person.class);

        return personRepoService.save(personData);
    }

    @Override
    public List<Person> getPersonStartWith(String name) {
        return personRepoService.findByfirstName(name);
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepoService.findAll();
    }

    @Override
    public String deletePersonDetails(String id) {
         personRepoService.deleteById(id);
         return "Sucessfully Deleted";
    }

    @Override
    public List<Person> getPersonByAge(int minAge, int maxAge) {
        return personRepoService.findPersonByAgeBetween(minAge,maxAge);
    }

    @Override
    public Page<Person> search(String firstName, String lastName, String minAge, String maxAge, String city, Pageable pageable) {
        Query query= new Query().with(pageable);
        List<Criteria>criteria= new ArrayList<>();
        if(firstName!= null && !firstName.isEmpty()){
            criteria.add(Criteria.where("firstName").is(firstName));
        }
        if (lastName!= null && !lastName.isEmpty()){
            criteria.add(Criteria.where("lastName").regex(lastName));
        }
        if (minAge!=null && maxAge!=null){
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }
        if (city!=null && !city.isEmpty()){
            criteria.add(Criteria.where("addresses.city").is(city));
        }
        if (!criteria.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        Page<Person> personPage= PageableExecutionUtils.getPage(mongoTemplate.find(query,Person.class), pageable, () ->mongoTemplate.count(query.skip(0).limit(0),Person.class));
        return personPage;
    }

    @Override
    public List<Document> getOldestPersonByCity() {
        UnwindOperation unwindOperation= Aggregation.unwind("addresses");
        SortOperation sortOperation=Aggregation.sort(Sort.Direction.DESC,"age");
//        GroupOperation groupOperation=Aggregation.group("addresses.city").max("age").as("maxAge");
        GroupOperation groupOperation= Aggregation.group("addresses.city").first(Aggregation.ROOT).as("oldPerson");
        Aggregation aggregation= Aggregation.newAggregation(unwindOperation,sortOperation,groupOperation);
        List<Document> person= mongoTemplate.aggregate(aggregation,Person.class,Document.class).getMappedResults();
//         for (AggregationResultDTO i: person){
//             AggregationResultDTO aggregationResultDTO=modelMapper.map(person,AggregationResultDTO.class);
//             result.add(aggregationResultDTO);
//         }
         return person;
    }

    @Override
    public List<Document> getPopulationByCity() {

        UnwindOperation unwindOperation= Aggregation.unwind("addresses");
        GroupOperation groupOperation= Aggregation.group("addresses.city").count().as("PopulationCount");
        SortOperation sortOperation= Aggregation.sort(Sort.Direction.DESC,"PopulationCount");
        ProjectionOperation projectionOperation=Aggregation.project().andExpression("_id").as("city").andExpression("PopulationCount").as("count");
        Aggregation aggregation= Aggregation.newAggregation(unwindOperation,groupOperation,sortOperation,projectionOperation);
        List<Document> person= mongoTemplate.aggregate(aggregation,Person.class,Document.class).getMappedResults();
        return person;
    }




}
