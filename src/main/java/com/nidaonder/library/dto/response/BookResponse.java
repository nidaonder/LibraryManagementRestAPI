package com.nidaonder.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private int id;
    private String name;
    private int publicationYear;
    private int stock;
    private int authorId;
    private int publisherId;
}
