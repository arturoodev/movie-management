package org.arturocode.moviemanagement.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "user_id", nullable = false)
    private Long userid;

    @Check(constraints = "rating >= 0 and rating <= 5")
    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "movie_id", updatable = false, insertable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;
}
