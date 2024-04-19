package edu.unimagdalena.api.model.dto;

import java.util.Collections;
import java.util.List;

public record CustomerDTO (
        Long id,
        String name,
        String email,
        String address,
        List<OrderDTO> orders
) {

    public List<OrderDTO> getCopyOfOrders() {
        return Collections.unmodifiableList(orders);
    }
}
