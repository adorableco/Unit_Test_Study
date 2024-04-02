package com.example.unitTestStudy.post.service;

import com.example.unitTestStudy.common.exception.NotFoundException;
import com.example.unitTestStudy.post.domain.Post;
import com.example.unitTestStudy.post.converter.PostConverter;
import com.example.unitTestStudy.post.repository.PostRepository;
import com.example.unitTestStudy.post.dto.PostCreateRequestDto;
import com.example.unitTestStudy.post.dto.PostEditRequestDto;
import com.example.unitTestStudy.post.dto.PostResponseDto;
import com.example.unitTestStudy.user.domain.User;
import com.example.unitTestStudy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.Sort.Direction.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostConverter postConverter;

    private static final int PAGE_SIZE = 5;

    @Transactional
    public Long save(PostCreateRequestDto postCreateRequestDto) {
        User user = userRepository.findById(postCreateRequestDto.getUserId())
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
        Post post = postConverter.toPost(postCreateRequestDto, user);
        Post entity = postRepository.save(post);
        return entity.getId();
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost (Long postId) {
        return postRepository.findById(postId)
                .map(postConverter::toPostResponseDto)
                .orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> getAllPosts (int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE,
                Sort.by(DESC, "createdAt"));
        return postRepository.findAll(pageRequest)
                .map(postConverter::toPostResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> getAllPostsByUserId (Long userId, int page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE,
                Sort.by(DESC, "createdAt"));
        return postRepository.findPostsByUserId(userId, pageRequest)
                .map(postConverter::toPostResponseDto);
    }

    @Transactional
    public Long edit(PostEditRequestDto postEditRequestDto) {
        Post post = postRepository.findById(postEditRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다."));
        post.changeTitle(postEditRequestDto.getTitle());
        post.changeContent(postEditRequestDto.getContent());
        return post.getId();
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시물을 찾을 수 없습니다."));
        postRepository.deleteById(postId);
    }
}