package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherUpdateRequest {

    @Positive
    private int id;

    @NotEmpty
    private String name;

    private int establishmentYear;

    private String address;
}
