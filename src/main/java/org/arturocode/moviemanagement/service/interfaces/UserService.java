package org.arturocode.moviemanagement.service.interfaces;

import org.arturocode.moviemanagement.persistence.entity.User;

import java.util.List;

public interface UserService {
    List<User> finaAll();

    List<User> findAllByUsername(String username);

    User findOneByUsername(String username);

    User createOne(User user);

    User updateOneByUsername(String username, User user);

    void deleteOneByUsername(String username);
}
