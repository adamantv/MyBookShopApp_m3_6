package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PostponedPageController {
    private final BookService bookService;

    @Autowired
    public PostponedPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("postponedBooks")
    private List<Book> postponedBooks() {
        return bookService.getBooksData();
    }

    @GetMapping("/postponed")
    public String postponedPage() {
        return "postponed";
    }
}
