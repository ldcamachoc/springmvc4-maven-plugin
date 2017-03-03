package com.accenture.service;

import com.accenture.model.Book;

import java.util.List;

/**
 * Created by l.camacho.carbajal on 2/9/2017.
 */
public interface BookService {
    List<Book> findByTitle(String title);
    List<Book> findAll();
}
