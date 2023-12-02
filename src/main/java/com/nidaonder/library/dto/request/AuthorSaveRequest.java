package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSaveRequest {

    @NotEmpty
    private String name;

    @Past
    private LocalDate birthDate;

    @NotEmpty
    private String country;
}
