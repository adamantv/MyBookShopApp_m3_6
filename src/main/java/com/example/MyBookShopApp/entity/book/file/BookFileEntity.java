package com.example.MyBookShopApp.entity.book.file;

import javax.persistence.*;

@Entity
@Table(name = "book_file")
public class BookFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String hash;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    BookFileEntity typeId;

    String path;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BookFileEntity getTypeId() {
        return typeId;
    }

    public void setTypeId(BookFileEntity typeId) {
        this.typeId = typeId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
