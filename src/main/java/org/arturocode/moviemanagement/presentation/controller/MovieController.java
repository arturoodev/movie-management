package org.arturocode.moviemanagement.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.arturocode.moviemanagement.exception.ObjectNotFoundException;
import org.arturocode.moviemanagement.presentation.dto.request.SaveMovie;
import org.arturocode.moviemanagement.presentation.dto.response.GetMovie;
import org.arturocode.moviemanagement.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    private MovieService movieService;

    @GetMapping()
    public ResponseEntity<List<GetMovie>> getAllMovies() {
//        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK); opcion1
//        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAll()); opcion2
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMovie> findOneById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.findOneById(id));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<GetMovie> createMovie(@Valid @RequestBody SaveMovie saveDto, HttpServletRequest request) {
        try {
            GetMovie movieSaved = movieService.createOne(saveDto);
            String baseUrl = request.getRequestURL().toString();
            URI uri = URI.create(baseUrl + "/" + movieSaved.id());

            return ResponseEntity.created(uri).body(movieSaved);
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetMovie> updateOne(@Valid @PathVariable Long id, @RequestBody SaveMovie saveDto) {
        try {
            GetMovie movieSaved = movieService.updateOne(id, saveDto);
            return ResponseEntity.ok(movieSaved);
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteOne(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
