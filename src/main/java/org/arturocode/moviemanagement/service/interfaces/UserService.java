package org.arturocode.moviemanagement.service.interfaces;

import org.arturocode.moviemanagement.presentation.dto.request.SaveUser;
import org.arturocode.moviemanagement.presentation.dto.response.GetUser;

import java.util.List;

public interface UserService {

    List<GetUser> finaAll();

    List<GetUser> findAllByUsername(String username);

    GetUser findOneByUsername(String username);

    GetUser createOne(SaveUser saveDto);

    GetUser updateOneByUsername(String username, SaveUser saveDto);

    void deleteOneByUsername(String username);
}
