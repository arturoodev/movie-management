package org.arturocode.moviemanagement.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveUser(
        @Pattern(regexp = "[a-zA-Z0-9-_]{8,255}", message = "{saveUser.username.pattern}")
        String name,

        @Size(max = 255, message = "{generics.size}")
        @NotBlank(message = "{generics.notBlank}")
        String username,

        @Size(min = 10, max = 255, message = "{generics.size}")
        @NotBlank(message = "{generics.notBlank}")
        String password,

        @Size(min = 10, max = 255, message = "{generics.size}") @JsonProperty(value = "confirm_password")
        String confirmPassword
) implements Serializable {
}
