package com.nidaonder.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", columnDefinition = "serial")
    public int id;

    @Column(name = "publisher_name")
    public String name;

    @Column(name = "publisher_establishment_year")
    public int establishmentYear;

    @Column(name = "publisher_address")
    public String address;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> bookList;
}
