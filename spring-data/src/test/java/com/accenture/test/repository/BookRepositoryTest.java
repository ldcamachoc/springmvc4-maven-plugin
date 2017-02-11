package com.accenture.test.repository;

import com.accenture.model.Book;
import com.accenture.test.config.PersistenceContextTest;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

/**
 * Created by l.camacho.carbajal on 2/7/2017.
 */
public class BookRepositoryTest extends PersistenceContextTest{
    @Test
    public void findByAll(){
        List<Book> books = bookRepository.findAll();
        Assert.assertTrue(books.size() == 3);
    }

    @Test
    public void findByTitle(){
        String titleExpected = "Java";
        List<Book> books = bookRepository.findByTitleLike(titleExpected);

        for (Book book: books) {
            String titleActual = book.getTitle();
            System.out.print("Title: "+titleActual);
            Assert.assertTrue(titleActual.contains(titleExpected));
        }


    }
}
