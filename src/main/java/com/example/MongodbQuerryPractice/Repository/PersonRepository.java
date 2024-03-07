package com.example.MongodbQuerryPractice.Repository;

import com.example.MongodbQuerryPractice.Model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

List<Person> findByfirstName(String name);
@Query(value = "{'age':{$gt:?0,$lt:?1}}",fields = "{addresses:0}")
List<Person> findPersonByAgeBetween(int minAge, int maxAge);
}
