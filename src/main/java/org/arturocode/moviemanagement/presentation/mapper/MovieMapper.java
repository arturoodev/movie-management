package org.arturocode.moviemanagement.presentation.mapper;

import org.arturocode.moviemanagement.persistence.entity.Movie;
import org.arturocode.moviemanagement.presentation.dto.request.SaveMovie;
import org.arturocode.moviemanagement.presentation.dto.response.GetMovie;

import java.util.List;

public class MovieMapper {
    public static GetMovie toGetDto(Movie entity) {
        if (entity == null) return null;

        return new GetMovie(
                entity.getId(),
                entity.getTitle(),
                entity.getDirector(),
                entity.getGenre(),
                entity.getReleaseYear(),
                RatingMapper.toGetMovieRatingDtoList(entity.getRatings()));
    }

    public static List<GetMovie> toGetDtoList(List<Movie> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(MovieMapper::toGetDto)
                .toList();
    }

    public static Movie toEntity(SaveMovie saveDto) {
        if (saveDto == null) return null;

        Movie newMovie = new Movie();
        newMovie.setTitle(saveDto.title());
        newMovie.setDirector(saveDto.director());
        newMovie.setGenre(saveDto.genre());
        newMovie.setReleaseYear(saveDto.releaseYear());
        return newMovie;
    }

    public static void updateEntity(Movie oldMovie, SaveMovie saveDto) {
        if(oldMovie == null || saveDto == null) return;

        oldMovie.setTitle(saveDto.title());
        oldMovie.setDirector(saveDto.director());
        oldMovie.setGenre(saveDto.genre());
        oldMovie.setReleaseYear(saveDto.releaseYear());
    }
}
