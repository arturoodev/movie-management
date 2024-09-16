package org.arturocode.moviemanagement.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private String director;
    private String genre;

    @Column(name = "release_year")
    private Integer releaseYear;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
    private List<Rating> ratings = new ArrayList<>();
}
