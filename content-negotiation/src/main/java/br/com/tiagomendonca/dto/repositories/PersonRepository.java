package br.com.tiagomendonca.dto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagomendonca.dto.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
