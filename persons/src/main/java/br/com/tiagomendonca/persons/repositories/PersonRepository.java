package br.com.tiagomendonca.persons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagomendonca.persons.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
