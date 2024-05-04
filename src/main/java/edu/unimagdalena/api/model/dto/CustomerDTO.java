package edu.unimagdalena.api.model.dto;

public record CustomerDTO(
        Long id,
        String name,
        String email,
        String roles,
        String address) {
}
