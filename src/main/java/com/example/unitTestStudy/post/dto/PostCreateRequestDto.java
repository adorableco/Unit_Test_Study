package com.example.unitTestStudy.post.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequestDto {
    private Long userId;

    private String title;

    private String content;
}