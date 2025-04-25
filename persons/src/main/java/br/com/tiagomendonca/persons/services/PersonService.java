package br.com.tiagomendonca.persons.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiagomendonca.persons.exception.ResourceNotFoundException;
import br.com.tiagomendonca.persons.model.Person;
import br.com.tiagomendonca.persons.repositories.PersonRepository;

@Service
public class PersonService {
    
    private Logger  logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Findng all Persons!");

        var persons = repository.findAll();
        return persons;
    }

    public Person findById(Long id) {
        logger.info("Findng one Person!");
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public Person create(Person person) {
        logger.info("Creating one Person!");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one Person!");

        Person entity = repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        
        repository.delete(entity);
    }
}
