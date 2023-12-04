package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingUpdateRequest {

    @Positive(message = "ID value must be positive!")
    private int id;

    @NotEmpty(message = "Borrower name can not be empty or null!")

    private String borrowerName;

    @Email
    private String borrowerEmail;

    @PastOrPresent
    private LocalDate borrowingDate;

    @Nullable
    @PastOrPresent
    private LocalDate returnDate;

    private int borrowingBook;
}
