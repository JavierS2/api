package edu.unimagdalena.api.model.dto;

import edu.unimagdalena.api.model.entities.Order;

public record ShipmentDetailsDTO(
        Long id,
        Order order,
        String shipmentAddress,
        String transporter,
        Long guideNumber) {
}