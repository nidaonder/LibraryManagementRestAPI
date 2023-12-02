package com.nidaonder.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    private int id;
    private String name;
    private LocalDate birthDate;
    private String country;
}
