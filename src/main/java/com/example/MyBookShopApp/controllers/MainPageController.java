package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.RecommendedBooksPageDto;
import com.example.MyBookShopApp.entity.book.Book;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() {
        return bookService.getBooksData();
    } //for future logic. will be added later

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookService.getBooksData();
    } //for future logic. will be added later

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public RecommendedBooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                                @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }


}
