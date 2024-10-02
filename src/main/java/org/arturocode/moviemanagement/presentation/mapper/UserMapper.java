package org.arturocode.moviemanagement.presentation.mapper;

import org.arturocode.moviemanagement.persistence.entity.User;
import org.arturocode.moviemanagement.presentation.dto.request.SaveUser;
import org.arturocode.moviemanagement.presentation.dto.response.GetUser;

import java.util.List;

public class UserMapper {
    public static GetUser toGetDto(User entity) {
        if (entity == null) return null;

        return new GetUser(
                entity.getName(),
                entity.getUsername(),
                RatingMapper.toGetUserRatingDtoList(entity.getRatings())
        );
    }

    public static List<GetUser> toGetDtoList(List<User> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(UserMapper::toGetDto)
                .toList();
    }

    public static User toEntity(SaveUser saveDto) {
        if (saveDto == null) return null;

        User newUser = new User();
        newUser.setName(saveDto.name());
        newUser.setUsername(saveDto.username());
        newUser.setPassword(saveDto.password());
        return newUser;
    }

    public static void updateEntity(User oldUser, SaveUser saveDto) {
        if(oldUser == null || saveDto == null) return;
        oldUser.setUsername(saveDto.username());
        oldUser.setPassword(saveDto.password());
    }
}
