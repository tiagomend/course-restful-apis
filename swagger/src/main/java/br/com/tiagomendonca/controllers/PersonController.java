package br.com.tiagomendonca.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiagomendonca.data.dto.PersonDto;
import br.com.tiagomendonca.services.PersonService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(
    value = "/persons",
    produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_YAML_VALUE
    },
    consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_YAML_VALUE
    })
public class PersonController implements PersonControllerDoc {
    
    @Autowired
    private PersonService service;

    @Override
    @GetMapping()
    public List<PersonDto> findAll() { return service.findAll(); }

    @Override
    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable("id") Long id) {
        var person = service.findById(id);
        person.setBirthDate(new Date());
        person.setPhoneNumber("+55 (16) 98765-4321");
        return person;
    }

    @Override
    @PostMapping()
    public PersonDto create(@RequestBody PersonDto person) {
        return service.create(person);
    }

    @Override
    @PutMapping()
    public PersonDto update(@RequestBody PersonDto person) {
        return service.update(person);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
