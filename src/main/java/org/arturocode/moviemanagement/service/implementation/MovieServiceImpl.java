package org.arturocode.moviemanagement.service.implementation;

import org.arturocode.moviemanagement.exception.ObjectNotFoundException;
import org.arturocode.moviemanagement.persistence.entity.Movie;
import org.arturocode.moviemanagement.persistence.repository.MovieRepository;
import org.arturocode.moviemanagement.persistence.util.MovieGenre;
import org.arturocode.moviemanagement.presentation.dto.request.SaveMovie;
import org.arturocode.moviemanagement.presentation.dto.response.GetMovie;
import org.arturocode.moviemanagement.presentation.mapper.MovieMapper;
import org.arturocode.moviemanagement.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<GetMovie> findAll() {
        List<Movie> entities = movieRepository.findAll();
        return MovieMapper.toGetDtoList(entities);
    }

    @Override
    public List<GetMovie> findAllByTitle(String title) {
        List<Movie> entities = movieRepository.findByTitleContaining(title);
        return MovieMapper.toGetDtoList(entities);
    }

    @Override
    public List<GetMovie> findAllByGenre(MovieGenre genre) {
        List<Movie> entities = movieRepository.findByGenre(genre);
        return MovieMapper.toGetDtoList(entities);
    }

    @Override
    public List<GetMovie> findAllByGenreAndTitle(MovieGenre genre, String title) {
        List<Movie> entities = movieRepository.findByGenreAndTitleContaining(genre, title);
        return MovieMapper.toGetDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public GetMovie findOneById(Long id) {
        return MovieMapper.toGetDto(this.finOneEntityById(id));
    }

    @Transactional(readOnly = true)
    private Movie finOneEntityById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Movie not found" + id));
        //.orElseThrow(() -> new ObjectNotFoundException("[movie: " + Long.toString(id) + "]"));
    }

    @Override
    public GetMovie createOne(SaveMovie saveDto) {
        Movie newMovie = MovieMapper.toEntity(saveDto);
        return MovieMapper.toGetDto(movieRepository.save(newMovie));
    }

    @Override
    public GetMovie updateOne(Long id, SaveMovie saveDto) {
        Movie oldMovie = this.finOneEntityById(id);
        MovieMapper.updateEntity(oldMovie, saveDto);

        return MovieMapper.toGetDto(movieRepository.save(oldMovie));
    }

    @Override
    public void deleteOne(Long id) {
        Movie movie = this.finOneEntityById(id);
        movieRepository.delete(movie);
    }
}
