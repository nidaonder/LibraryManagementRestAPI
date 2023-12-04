package com.nidaonder.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "book_borrowing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private int id;

    @NotEmpty
    @Column(name = "borrower_name")
    private String borrowerName;

    @Email
    @NotEmpty
    private String borrowerEmail;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrowing_date")
    private LocalDate borrowingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne()
    @JoinColumn(name = "borrowing_book", referencedColumnName = "book_id")
    private Book book;
}
