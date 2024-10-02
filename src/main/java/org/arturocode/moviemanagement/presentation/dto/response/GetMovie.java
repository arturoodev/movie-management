package org.arturocode.moviemanagement.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;

import java.io.Serializable;
import java.util.List;

public record GetMovie(
        long id,
        String title,
        String director,
        MovieGenre genre,
        @JsonProperty(value = "release_year") int releaseYear,
        List<GetRating> ratings
) implements Serializable {

    public static record GetRating(
            long id,
            int rating,
            String username
    ) implements Serializable {

    }
}
