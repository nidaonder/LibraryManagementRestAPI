package com.nidaonder.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "serial")
    private int id;

    @NotNull
    @Column(name = "book_name")
    private String name;

    @Column(name = "book_publication_year")
    private int publicationYear;

    @Column(name = "stock")
    private int stock;

    @ManyToOne()
    @JoinColumn(name = "book_author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToOne()
    @JoinColumn(name = "book_publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BookBorrowing> borrowingList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book2categories",
            joinColumns = {@JoinColumn(name = "book2categories_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "book2categories_category_id")}
    )
    @JsonIgnore
    private List<Category> categoryList;

}
