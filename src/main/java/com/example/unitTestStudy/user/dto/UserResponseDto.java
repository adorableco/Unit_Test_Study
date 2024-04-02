package com.example.unitTestStudy.user.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {
    private Long id;
    private String name;
    private int age;
    private String hobby;
}