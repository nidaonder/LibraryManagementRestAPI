package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherSaveRequest {

    @NotEmpty
    private String name;

    private int establishmentYear;

    private String address;
}
