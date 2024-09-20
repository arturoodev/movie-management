package org.arturocode.moviemanagement.service.implementation;

import org.arturocode.moviemanagement.exception.ObjectNotFoundException;
import org.arturocode.moviemanagement.persistence.entity.Rating;
import org.arturocode.moviemanagement.persistence.repository.RatingRepository;
import org.arturocode.moviemanagement.service.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> findAllByMovieId(Long movieId) {
        return ratingRepository.findByMovieId(movieId);
    }

    @Override
    public List<Rating> findAllByUsername(String username) {
        return ratingRepository.findByUsername(username);
    }

    @Override
    public Rating findOneById(Long id) {
        return ratingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("[movie: " + Long.toString(id) + "]"));
    }

    @Override
    public Rating createOne(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateOneById(Long id, Rating newRating) {
        Rating oldRating = this.findOneById(id);
        oldRating.setUserid(newRating.getUserid());
        oldRating.setMovieId(newRating.getMovieId());
        return ratingRepository.save(oldRating);
    }

    @Override
    public void deleteOneById(Long id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
            return;
        }
        throw new ObjectNotFoundException("[movie: " + Long.toString(id) + "] not found");
        //Es menos costoso hablando de recursos hacerlo de esta forma porque
        //el findOneById trae todas las columnas y el existById solo comprueba si existe o no.
    }
}
