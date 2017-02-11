package com.accenture.service.impl;

import com.accenture.dao.repository.BookRepository;
import com.accenture.model.Book;
import com.accenture.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by l.camacho.carbajal on 2/9/2017.
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleLike(title);
    }
}
