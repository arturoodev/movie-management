package org.arturocode.moviemanagement.persistence.repository;

import org.arturocode.moviemanagement.persistence.entity.Movie;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByGenre(MovieGenre genre);

    List<Movie> findByGenreAndTitleContaining(MovieGenre genre, String title);
}
