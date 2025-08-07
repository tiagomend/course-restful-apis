package br.com.tiagomendonca.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.tiagomendonca.controllers.PersonController;
import br.com.tiagomendonca.data.dto.PersonDto;
import br.com.tiagomendonca.exception.RequiredObjectIsNullException;
import br.com.tiagomendonca.exception.ResourceNotFoundException;
import static br.com.tiagomendonca.mapper.ObjectMapper.parseListObjects;
import static br.com.tiagomendonca.mapper.ObjectMapper.parseObject;
import br.com.tiagomendonca.model.Person;
import br.com.tiagomendonca.repositories.PersonRepository;

@Service
public class PersonService {
    
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDto> findAll() {
        logger.info("Findng all Persons!");

        var persons = parseListObjects(repository.findAll(), PersonDto.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public PersonDto findById(Long id) {
        logger.info("Findng one Person!");
        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        var dto = parseObject(entity, PersonDto.class);
        addHateoasLinks(dto);
        return dto;
    }

    
    public PersonDto create(PersonDto person) {
        if (person == null)throw new RequiredObjectIsNullException();
        logger.info("Creating one Person!");
        
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDto.class);
        addHateoasLinks(dto);
        return dto;
    }
    
    public PersonDto update(PersonDto person) {
        if (person == null)throw new RequiredObjectIsNullException();
        logger.info("Updating one Person!");
        
        Person entity = repository.findById(person.getId())
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        
        var dto = parseObject(repository.save(entity), PersonDto.class);
        addHateoasLinks(dto);
        return dto;
    }
    
    public void delete(Long id) {
        logger.info("Deleting one Person!");
        
        Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        
        repository.delete(entity);
    }

    private void addHateoasLinks(PersonDto dto) {
        dto.add(linkTo(methodOn(PersonController.class)
            .findById(dto.getId()))
            .withSelfRel()
            .withType("GET")
        );

        dto.add(linkTo(methodOn(PersonController.class)
        .findAll())
        .withRel("findAll")
        .withType("GET")
        );

        dto.add(linkTo(methodOn(PersonController.class)
        .create(dto))
        .withRel("create")
        .withType("POST")
        );

        dto.add(linkTo(methodOn(PersonController.class)
        .update(dto))
        .withRel("update")
        .withType("PUT")
        );
        
        dto.add(linkTo(methodOn(PersonController.class)
            .delete(dto.getId()))
            .withRel("delete")
            .withType("DELETE")
        );
    }
}
