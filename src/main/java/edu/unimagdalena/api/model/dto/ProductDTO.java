package edu.unimagdalena.api.model.dto;

import java.util.Collections;
import java.util.List;

public record ProductDTO (
                Long id,
                String name,
                Float price,
                Integer stock
) { }
