package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorUpdateRequest {

    @Positive(message = "ID value must be positive!")
    private int id;

    @NotEmpty(message = "Author name can not be empty or null!")
    private String name;

    @Past(message = "The birth date must be a past date.")
    private LocalDate birthDate;

    @NotEmpty(message = "Country can not be empty or null!")
    private String country;


}
