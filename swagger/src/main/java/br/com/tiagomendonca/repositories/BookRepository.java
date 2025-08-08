package br.com.tiagomendonca.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiagomendonca.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}