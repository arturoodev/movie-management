package org.arturocode.moviemanagement.service.interfaces;

import org.arturocode.moviemanagement.persistence.entity.Movie;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;
import org.arturocode.moviemanagement.presentation.dto.request.SaveMovie;
import org.arturocode.moviemanagement.presentation.dto.response.GetMovie;

import java.util.List;

public interface MovieService {
    List<GetMovie> findAll();

    List<GetMovie> findAllByTitle(String title);

    List<GetMovie> findAllByGenre(MovieGenre genre);

    List<GetMovie> findAllByGenreAndTitle(MovieGenre genre, String title);

    GetMovie findOneById(Long id);

    GetMovie createOne(SaveMovie saveDto);

    GetMovie updateOne(Long id, SaveMovie saveDto);

    void deleteOne(Long id);
}
