package com.nidaonder.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @NotEmpty
    @Column(name = "category_name")
    private String name;

    @Column(name = "category_description")
    private String description;

    @ManyToMany(mappedBy = "categoryList", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> bookList;
}
