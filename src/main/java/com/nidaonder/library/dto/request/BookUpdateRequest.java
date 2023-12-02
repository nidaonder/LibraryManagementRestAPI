package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    @Positive(message = "ID value must be positive!")
    private int id;

    @NotEmpty(message = "Book name can not be empty or null!")
    private String name;

    @PositiveOrZero
    private int publicationYear;

    @PositiveOrZero
    private int stock;

    private int authorId;

    private int publisherId;
}
