package com.nidaonder.library.dao;

import com.nidaonder.library.entities.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepo extends JpaRepository<BookBorrowing, Integer> {
}
