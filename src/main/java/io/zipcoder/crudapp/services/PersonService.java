package io.zipcoder.crudapp.services;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository repository;
    @Autowired
    public PersonService(PersonRepository personRepository){this.repository = personRepository;}

    public Iterable<Person> index(){return repository.findAll();}

    public Person show(Long id){return repository.findOne(id); }

    public Person create(Person person){ return repository.save(person);}

    public Person update(Long id, Person newPersonData){
        Person originalPerson = repository.findOne(id);
        originalPerson.setFname(newPersonData.getFname());
        originalPerson.setLname(newPersonData.getLname());
        return repository.save(originalPerson);
    }

    public Boolean delete(Long id){
        repository.delete(id);
        return true;
    }


}
