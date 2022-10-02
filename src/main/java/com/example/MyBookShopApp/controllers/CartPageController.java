package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CartPageController {
    private final BookService bookService;

    @Autowired
    public CartPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("booksFromCard")
    private List<Book> booksFromCard() {
        return bookService.getPostponedBooks();
    }


    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }
}
