package org.arturocode.moviemanagement.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;

import java.io.Serializable;

public record SaveMovie(
    String title,
    String director,
    MovieGenre genre,
    @JsonProperty(value = "release_year") int releaseYear
) implements Serializable {
}
