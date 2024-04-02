package com.example.unitTestStudy.user.converter;

import com.example.unitTestStudy.user.domain.User;
import com.example.unitTestStudy.user.dto.UserCreateRequestDto;
import com.example.unitTestStudy.user.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    // entity -> dto
    public UserResponseDto toUserResponseDto (User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .hobby(user.getHobby())
                .build();
    }

    // dto -> entity
    public User toUser (UserCreateRequestDto requestDto) {
        return new User(requestDto.getName(), requestDto.getAge(), requestDto.getHobby());
    }
}