package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findTop20ByOrderById();
    }

    public List<Book> getPostponedBooks() {
        return bookRepository.findTop3ByOrderById();
    }

    public List<Book> getBooksFromCart() {
        return bookRepository.findTop3ByOrderById();
    }
}