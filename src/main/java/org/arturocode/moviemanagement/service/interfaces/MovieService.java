package org.arturocode.moviemanagement.service.interfaces;

import org.arturocode.moviemanagement.persistence.entity.Movie;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> findAllByTitle(String title);

    List<Movie> findAllByGenre(MovieGenre genre);

    List<Movie> findAllByGenreAndTitle(MovieGenre genre, String title);

    Movie findOneById(Long id);

    Movie createOne(Movie movie);

    Movie updateOne(Long id, Movie movie);

    void deleteOne(Long id);
}
