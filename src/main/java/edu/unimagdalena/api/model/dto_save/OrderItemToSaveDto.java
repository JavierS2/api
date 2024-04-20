package edu.unimagdalena.api.model.dto_save;

import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.dto.ProductDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderItemToSaveDto (

        @NotNull(message = "Order is mandatory")
        OrderDTO order,

        @NotNull(message = "Product is mandatory")
        ProductDTO product,

        @PositiveOrZero
        @NotNull(message = "Amount is mandatory")
        Integer amount,

        @NotNull(message = "Unit price is mandatory")
        @PositiveOrZero
        Float unitPrice
) { }
