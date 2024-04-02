package com.example.unitTestStudy.post.dto;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
}