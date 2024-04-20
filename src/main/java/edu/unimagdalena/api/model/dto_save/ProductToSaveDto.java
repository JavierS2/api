package edu.unimagdalena.api.model.dto_save;

import jakarta.validation.constraints.*;

public record ProductToSaveDto (

        @Size(min = 3, max = 40)
        @NotEmpty(message = "Name is mandatory")
        String name,

        @Positive
        @NotNull(message = "Price is mandatory")
        Float price,

        @PositiveOrZero
        @NotNull(message = "Stock is mandatory")
        Integer stock
) { }
