package com.accenture.test.service;

import com.accenture.model.Book;
import com.accenture.service.BookService;
import com.accenture.test.config.ServiceContexTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by l.camacho.carbajal on 2/10/2017.
 */
public class BookServiceTest extends ServiceContexTest {

    @Test
    public void findByTitle(){
        String titleExpected = "Java";
        List<Book> books = bookService.findByTitle(titleExpected);

        books.forEach(book -> {
            String titleActual = book.getTitle();
            System.out.println(titleActual);
            Assert.assertTrue(titleActual.contains(titleExpected));
        });
    }
}
