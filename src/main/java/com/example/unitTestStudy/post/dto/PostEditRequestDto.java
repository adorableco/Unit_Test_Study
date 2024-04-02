package com.example.unitTestStudy.post.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEditRequestDto {
    Long postId;
    String title;
    String content;
}