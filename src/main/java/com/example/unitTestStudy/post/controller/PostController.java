package com.example.unitTestStudy.post.controller;

import com.example.unitTestStudy.common.dto.ApiResponse;
import com.example.unitTestStudy.post.dto.PostCreateRequestDto;
import com.example.unitTestStudy.post.dto.PostEditRequestDto;
import com.example.unitTestStudy.post.dto.PostResponseDto;
import com.example.unitTestStudy.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ApiResponse<Page<PostResponseDto>> getAllPosts (@RequestParam int page) {
        Page<PostResponseDto> posts = postService.getAllPosts(page);
        return ApiResponse.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ApiResponse<PostResponseDto> getPost (@PathVariable("id") Long postId) {
        PostResponseDto post = postService.getPost(postId);
        return ApiResponse.ok(post);
    }

    @PostMapping("/posts")
    public ApiResponse<Long> savePost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        Long postId = postService.save(postCreateRequestDto);
        return ApiResponse.ok(postId);
    }

    @PutMapping("/posts")
    public ApiResponse<Long> editPost (@RequestBody PostEditRequestDto postEditRequestDto) {
        Long postId = postService.edit(postEditRequestDto);
        return ApiResponse.ok(postId);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse<Long> deletePost (@PathVariable Long postId) {
        postService.deletePost(postId);
        return ApiResponse.ok(postId);
    }
}
