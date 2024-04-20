package edu.unimagdalena.api.model.dto;

public record OrderItemDTO(
                Long id,
                OrderDTO order,
                ProductDTO product,
                Integer amount,
                Float unitPrice) {
}
