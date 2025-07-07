package br.com.tiagomendonca.dto.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiagomendonca.dto.data.dto.PersonDto;
import br.com.tiagomendonca.dto.exception.ResourceNotFoundException;
import static br.com.tiagomendonca.dto.mapper.ObjectMapper.parseListObjects;
import static br.com.tiagomendonca.dto.mapper.ObjectMapper.parseObject;
import br.com.tiagomendonca.dto.model.Person;
import br.com.tiagomendonca.dto.repositories.PersonRepository;

@Service
public class PersonService {
    
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDto> findAll() {
        logger.info("Findng all Persons!");

        var persons = parseListObjects(repository.findAll(), PersonDto.class);
        return persons;
    }

    public PersonDto findById(Long id) {
        logger.info("Findng one Person!");
        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        return parseObject(entity, PersonDto.class);
    }

    public PersonDto create(PersonDto person) {
        logger.info("Creating one Person!");

        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDto.class);
    }

    public PersonDto update(PersonDto person) {
        logger.info("Updating one Person!");

        Person entity = repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDto.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        
        repository.delete(entity);
    }
}
