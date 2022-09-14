package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData(){

        List<Book> books = jdbcTemplate.query("SELECT b.id AS id, b.author_id AS author_id, " +
                "b.title AS title, b.price_old AS price_old, b.price AS price, " +
                "a.first_name AS first_name, a.last_name AS last_name " +
                "FROM books b LEFT JOIN authors a " +
                "ON b.author_id = a.id", (ResultSet rs, int rownum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("first_name") + " " + rs.getString("last_name"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getInt("price_old"));
            book.setPrice(rs.getInt("price"));
            return book;
        });
        return new ArrayList<>(books);
    }
}
