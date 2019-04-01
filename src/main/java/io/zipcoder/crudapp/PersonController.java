package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;


    public PersonController(PersonRepository repository){this.repository = repository;}

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }


    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPersonList(){
        List<Person> list = new ArrayList<>();
        repository.findAll().forEach(s -> list.add(s));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        Long id = person.getId();
        Person originalPerson = repository.findOne(id);
        originalPerson.setFirstName(person.getFirstName());
        originalPerson.setLastName(person.getLastName());
        return new ResponseEntity<>(repository.save(originalPerson), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> DeletePerson(@PathVariable Long id){
        repository.delete(id);
        return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
    }
}
