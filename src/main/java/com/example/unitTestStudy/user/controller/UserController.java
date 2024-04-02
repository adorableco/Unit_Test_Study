package com.example.unitTestStudy.user.controller;

import com.example.unitTestStudy.common.dto.ApiResponse;
import com.example.unitTestStudy.user.dto.UserCreateRequestDto;
import com.example.unitTestStudy.user.dto.UserResponseDto;
import com.example.unitTestStudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ApiResponse<UserResponseDto> getOne(@PathVariable("id") Long userId) {
        UserResponseDto userResponseDto = userService.findOne(userId);
        return ApiResponse.ok(userResponseDto);
    }

    @PostMapping("/users")
    public ApiResponse<Long> saveUser(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        Long userId = userService.save(userCreateRequestDto);
        return ApiResponse.ok(userId);
    }
}