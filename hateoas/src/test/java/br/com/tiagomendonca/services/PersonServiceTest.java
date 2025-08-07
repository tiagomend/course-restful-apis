package br.com.tiagomendonca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tiagomendonca.data.dto.PersonDto;
import br.com.tiagomendonca.exception.RequiredObjectIsNullException;
import br.com.tiagomendonca.model.Person;
import br.com.tiagomendonca.repositories.PersonRepository;
import br.com.tiagomendonca.unittests.mocks.MockPerson;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp(){
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDto dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);
        var result = service.create(dto);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(thereIsLink(result, "self", "/persons/1", "GET"));
        assertTrue(thereIsLink(result, "create", "/persons", "POST"));
        assertTrue(thereIsLink(result, "update", "/persons", "PUT"));
        assertTrue(thereIsLink(result, "delete", "/persons/1", "DELETE"));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNull() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
        () -> {
            service.create(null);
        });

        String expectedMessage = "Is is not allowed to persist a null object";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDelete() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testFindAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDto> people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());
    }

    @Test
    void testFindById() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(thereIsLink(result, "self", "/persons/1", "GET"));
        assertTrue(thereIsLink(result, "create", "/persons", "POST"));
        assertTrue(thereIsLink(result, "update", "/persons", "PUT"));
        assertTrue(thereIsLink(result, "delete", "/persons/1", "DELETE"));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdate() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDto dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);
        var result = service.update(dto);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(thereIsLink(result, "self", "/persons/1", "GET"));
        assertTrue(thereIsLink(result, "create", "/persons", "POST"));
        assertTrue(thereIsLink(result, "update", "/persons", "PUT"));
        assertTrue(thereIsLink(result, "delete", "/persons/1", "DELETE"));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNull() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
        () -> {
            service.update(null);
        });

        String expectedMessage = "Is is not allowed to persist a null object";
        assertEquals(expectedMessage, exception.getMessage());
    }

    private boolean thereIsLink(PersonDto result, String rel, String href, String type) {
        return result.getLinks().stream()
            .anyMatch(link -> link.getRel().value().equals(rel) 
            && link.getHref().endsWith(href)
            && link.getType().equals(type));
    }
}
