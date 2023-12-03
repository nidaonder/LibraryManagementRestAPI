package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingSaveRequest {

    @NotEmpty
    private String borrowerName;

    @Email
    private String borrowerEmail;

    @PastOrPresent
    private LocalDate borrowingDate;

    @Nullable
    @PastOrPresent
    private LocalDate returnDate;

    private int borrowingBookId;
}
