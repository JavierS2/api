package edu.unimagdalena.api.model.dto_save;

import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record PaymentToSaveDto (

        @NotNull(message = "Order is mandatory")
        OrderDTO order,

        @PositiveOrZero
        @NotNull(message = "Total payment is mandatory")
        Float totalPayment,

        @NotNull(message = "Payment date is mandatory")
        LocalDateTime paymentDate,

        @NotNull(message = "Payment method is mandatory")
        PaymentMethod paymentMethod
) { }
