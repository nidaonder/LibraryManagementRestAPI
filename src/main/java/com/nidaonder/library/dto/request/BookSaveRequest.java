package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSaveRequest {

    @NotEmpty
    private String name;

    @Positive
    private int publicationYear;

    @PositiveOrZero
    @NotNull
    private int stock;

    private int authorId;

    private int publisherId;
}
