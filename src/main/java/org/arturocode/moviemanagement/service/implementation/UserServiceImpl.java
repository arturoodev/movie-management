package org.arturocode.moviemanagement.service.implementation;

import org.arturocode.moviemanagement.exception.ObjectNotFoundException;
import org.arturocode.moviemanagement.persistence.entity.User;
import org.arturocode.moviemanagement.persistence.repository.UserRepository;
import org.arturocode.moviemanagement.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> finaAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByUsername(String username) {
        return userRepository.findByUsernameContaining(username);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("[movie: " + username + "]"));
    }

    @Override
    public User createOne(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOneByUsername(String username, User user) {
        User oldUser = this.findOneByUsername(username);
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());

        return userRepository.save(oldUser);
    }

    @Override
    public void deleteOneByUsername(String username) {
//        User user = this.findOneByUsername(username);
//        userRepository.delete(user);
        if (userRepository.deleteByUsername(username) != 1) {
            throw new ObjectNotFoundException("[movie: " + username + "] not found");
        }
    }
}
