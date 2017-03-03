package com.accenture.web.controller;

import com.accenture.model.Book;
import com.accenture.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by l.camacho.carbajal on 2/16/2017.
 */
@Controller
@RequestMapping("/booksLibrary")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    @RequestMapping(value = "/findAll")
    public String findAllBooks(ModelMap modelMap){
        List<Book> books =bookService.findAll();

        modelMap.addAttribute("books", books);

        return "booksHtml";
    }


}
