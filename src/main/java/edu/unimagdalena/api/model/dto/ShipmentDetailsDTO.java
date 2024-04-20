package edu.unimagdalena.api.model.dto;

public record ShipmentDetailsDTO(
                Long id,
                OrderDTO order,
                String shipmentAddress,
                String transporter,
                Long guideNumber) {
}