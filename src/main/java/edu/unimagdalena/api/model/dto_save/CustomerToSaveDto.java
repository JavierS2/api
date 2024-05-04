package edu.unimagdalena.api.model.dto_save;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerToSaveDto (

        @NotBlank(message = "Name is mandatory")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Password is mandatory")
        @NotNull(message = "Password is mandatory")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,

        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Roles is mandatory")
        String roles,

        @NotBlank(message = "Address is mandatory")
        @Size(min = 5, message = "Address must be at least 5 characters")
        String address

) { }
