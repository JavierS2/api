package edu.unimagdalena.api.model.dto_save;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import edu.unimagdalena.api.model.dto.OrderDTO;


public record ShipmentDetailsToSaveDto(

        @NotNull(message = "Order is mondatory")
        OrderDTO order,

        @NotEmpty(message = "ShipmentAddress is mondatory")
        String shipmentAddress,

        @NotEmpty(message = "Transporter is mondatory")
        String transporter,

        @NotNull(message = "GuideNumber is mondatory")
        Long guideNumber
) { }
