package com.nidaonder.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequest {

    @Positive(message = "ID value must be positive!")
    private int id;

    @NotEmpty(message = "Category name can not be empty or null!")
    private String name;

    private String description;
}
