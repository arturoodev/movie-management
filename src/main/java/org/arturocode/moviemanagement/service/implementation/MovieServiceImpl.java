package org.arturocode.moviemanagement.service.implementation;

import org.arturocode.moviemanagement.exception.ObjectNotFoundException;
import org.arturocode.moviemanagement.persistence.entity.Movie;
import org.arturocode.moviemanagement.persistence.repository.MovieRepository;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;
import org.arturocode.moviemanagement.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findAllByTitle(String title) {
        return movieRepository.findByTitleContaining(title);
    }

    @Override
    public List<Movie> findAllByGenre(MovieGenre genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> findAllByGenreAndTitle(MovieGenre genre, String title) {
        return movieRepository.findByGenreAndTitleContaining(genre, title);
    }

    @Override
    public Movie findOneById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("[movie: " + Long.toString(id) + "]"));
    }

    @Override
    public Movie createOne(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateOne(Long id, Movie newMovie) {
        Movie oldMovie = this.findOneById(id);
        oldMovie.setTitle(newMovie.getTitle());
        oldMovie.setDirector(newMovie.getDirector());
        oldMovie.setGenre(newMovie.getGenre());
        oldMovie.setReleaseYear(newMovie.getReleaseYear());

        return movieRepository.save(oldMovie);
    }

    @Override
    public void deleteOne(Long id) {
        Movie movie = this.findOneById(id);
        movieRepository.delete(movie);
    }
}
