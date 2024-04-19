package edu.unimagdalena.api.model.dto;

import java.time.LocalDateTime;

import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.enums.PaymentMethod;

public record PaymentDTO (
                Long id,
                Order order,
                Float totalPayment,
                LocalDateTime paymentDate,
                PaymentMethod paymentMethod
) { }
