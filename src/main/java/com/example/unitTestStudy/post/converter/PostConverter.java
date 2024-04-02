package com.example.unitTestStudy.post.converter;

import com.example.unitTestStudy.post.domain.Post;
import com.example.unitTestStudy.post.dto.PostCreateRequestDto;
import com.example.unitTestStudy.post.dto.PostResponseDto;
import com.example.unitTestStudy.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    //dto -> entity
    public Post toPost (PostCreateRequestDto requestDto, User user) {
        Post post = new Post(requestDto.getTitle(), requestDto.getContent());
        post.setUser(user);
        return post;
    }

    //entity -> dto
    public PostResponseDto toPostResponseDto (Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getId())
                .build();
    }
}
