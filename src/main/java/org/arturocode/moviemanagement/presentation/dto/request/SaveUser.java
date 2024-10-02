package org.arturocode.moviemanagement.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record SaveUser(
    String name,
    String username,
    String password,
    @JsonProperty(value = "confirm_password") String confirmPassword
) implements Serializable {
}
