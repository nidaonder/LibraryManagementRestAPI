package com.nidaonder.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponse {
    private int id;
    private String name;
    private int establishmentYear;
}
