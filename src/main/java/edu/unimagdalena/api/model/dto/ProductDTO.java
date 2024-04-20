package edu.unimagdalena.api.model.dto;


public record ProductDTO (
                Long id,
                String name,
                Float price,
                Integer stock
) { }
