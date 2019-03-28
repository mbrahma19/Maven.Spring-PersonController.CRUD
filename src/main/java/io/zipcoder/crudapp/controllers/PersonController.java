package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private PersonRepository repository;

    public PersonController(PersonRepository repository){this.repository = repository;}

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    public ResponseEntity<List<Person>> getPersonList(){
        //return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);

        return null;
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(Person person){
        Long id = person.getId();
        Person originalPerson = repository.findOne(id);
        originalPerson.setFname(person.getFname());
        originalPerson.setLname(person.getLname());
        return new ResponseEntity<>(repository.save(originalPerson), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> DeletePerson(@PathVariable Long id){
        repository.delete(id);
        return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
    }
}
