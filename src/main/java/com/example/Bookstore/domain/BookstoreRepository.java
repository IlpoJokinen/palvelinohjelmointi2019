package com.example.Bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookstoreRepository extends CrudRepository<Book, Long> {

}
