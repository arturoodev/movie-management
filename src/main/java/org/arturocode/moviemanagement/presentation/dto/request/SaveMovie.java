package org.arturocode.moviemanagement.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;

import java.io.Serializable;

public record SaveMovie(

        @Size(min = 4, max = 255, message = "{generics.size}")
        @NotBlank(message = "{generics.notBlank}")
        String title,

        @Size(min = 4, max = 255, message = "{generics.size}")
        @NotBlank(message = "{generics.notBlank}")
        String director,

        @NonNull MovieGenre genre,

        @Min(value = 1900, message = "{generics.min}")
        @JsonProperty(value = "release_year")
        int releaseYear
) implements Serializable {
}
