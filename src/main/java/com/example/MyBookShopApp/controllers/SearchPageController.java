package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.book.Book;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


@Controller
public class SearchPageController {
    private final BookService bookService;

    @Autowired
    public SearchPageController(BookService bookService) {
        this.bookService = bookService;
    }

    //for future logic. will be added later
    @ModelAttribute("searchResult")
    public List<Book> searchResult() {
        return bookService.getBooksData();
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search/index";
    }

}
