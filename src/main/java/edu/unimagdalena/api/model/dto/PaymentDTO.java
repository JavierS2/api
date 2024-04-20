package edu.unimagdalena.api.model.dto;

import java.time.LocalDateTime;

import edu.unimagdalena.api.model.enums.PaymentMethod;

public record PaymentDTO(
        Long id,
        OrderDTO order,
        Float totalPayment,
        LocalDateTime paymentDate,
        PaymentMethod paymentMethod) {
}
