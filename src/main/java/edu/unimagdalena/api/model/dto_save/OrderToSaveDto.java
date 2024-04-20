package edu.unimagdalena.api.model.dto_save;

import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record OrderToSaveDto (

        @NotNull(message = "Customer is mandatory")
        CustomerDTO customer,

        @NotNull(message = "Order date is mandatory")
        LocalDateTime orderDate,

        @NotNull(message = "Status is mandatory")
        OrderStatus status,

        @NotNull(message = "List of items is mandatory")
        List<OrderItemDTO> items,

        @NotNull(message = "Payment ID is mandatory")
        PaymentDTO payment,

        @NotNull(message = "ShipmentDetails is mandatory")
        ShipmentDetailsDTO shipmentDetails
) { }
