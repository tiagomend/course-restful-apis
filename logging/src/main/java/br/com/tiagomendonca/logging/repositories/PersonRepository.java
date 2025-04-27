package br.com.tiagomendonca.logging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagomendonca.logging.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
