package org.arturocode.moviemanagement.service.implementation;

import org.arturocode.moviemanagement.exception.ObjectNotFoundException;
import org.arturocode.moviemanagement.persistence.entity.User;
import org.arturocode.moviemanagement.persistence.repository.UserRepository;
import org.arturocode.moviemanagement.presentation.dto.request.SaveUser;
import org.arturocode.moviemanagement.presentation.dto.response.GetUser;
import org.arturocode.moviemanagement.presentation.mapper.UserMapper;
import org.arturocode.moviemanagement.service.interfaces.UserService;
import org.arturocode.moviemanagement.service.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<GetUser> finaAll() {
        List<User> entities = userRepository.findAll();
        return UserMapper.toGetDtoList(entities);
    }

    @Override
    public List<GetUser> findAllByUsername(String username) {
        List<User> entities = userRepository.findByUsernameContaining(username);
        return UserMapper.toGetDtoList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public GetUser findOneByUsername(String username) {
        return UserMapper.toGetDto(this.findOneEntityByUsername(username));
    }

    @Transactional(readOnly = true)
    private User findOneEntityByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "User not found" + username));
    }

    @Override
    public GetUser createOne(SaveUser saveDto) {
        PasswordValidator.validatePassword(saveDto.password(), saveDto.confirmPassword());
        User newUSer = UserMapper.toEntity(saveDto);
        return UserMapper.toGetDto(userRepository.save(newUSer));
    }

    @Override
    public GetUser updateOneByUsername(String username, SaveUser saveDto) {
        PasswordValidator.validatePassword(saveDto.password(), saveDto.confirmPassword());
        User oldUser = this.findOneEntityByUsername(username);
        UserMapper.updateEntity(oldUser, saveDto);

        return UserMapper.toGetDto(userRepository.save(oldUser));
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
