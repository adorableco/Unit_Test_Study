package com.example.unitTestStudy.post.service;

import com.example.unitTestStudy.common.exception.NotFoundException;
import com.example.unitTestStudy.post.converter.PostConverter;
import com.example.unitTestStudy.post.domain.Post;
import com.example.unitTestStudy.post.dto.PostCreateRequestDto;
import com.example.unitTestStudy.post.dto.PostEditRequestDto;
import com.example.unitTestStudy.post.dto.PostResponseDto;
import com.example.unitTestStudy.post.repository.PostRepository;
import com.example.unitTestStudy.user.domain.User;
import com.example.unitTestStudy.user.repository.UserRepository;
import com.example.unitTestStudy.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.data.domain.Sort.Direction.DESC;

@ExtendWith({MockitoExtension.class})
class PostServiceTest {

    @InjectMocks
    private PostService sut;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    private PostConverter postConverter;


    @Test
    @DisplayName("포스트 저장에 성공한다.")
    void save_the_Post() {
        User member = createMember(1L);
        Post post = createPost();
        PostCreateRequestDto requestDto = createPostDto(post, member);
        Mockito.when(postRepository.save(any())).thenReturn(post);
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(member));

        Long savedId = sut.save(requestDto);

        assertThat(savedId).isEqualTo(post.getId());
    }

    @Test
    @DisplayName("postId로 포스트를 조회하는 것을 성공한다.")
    void get_the_Post() {
        Post post = createPost();
        Long postId = 1L;
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostResponseDto responseDto = sut.getPost(postId);

        assertThat(responseDto.getTitle()).isEqualTo(post.getTitle());
    }

    @Test
    @DisplayName("DB에 저장된 모든 포스트를 조회하는 것을 성공한다.")
    void getAllPosts() {
        List<Post> list = new ArrayList<>();
        list.add(createPost());
        list.add(createPost());
        Page<Post> page = new PageImpl<>(list);
        int pageNum = 10;
        PageRequest pageRequest = PageRequest.of(pageNum, 5, Sort.by(DESC, "createdAt"));
        Mockito.when(postRepository.findAll(pageRequest)).thenReturn(page);

        Page<PostResponseDto> allPosts = sut.getAllPosts(pageNum);

        assertThat(allPosts.getTotalElements()).isEqualTo(2);
    }

    @Test
    @DisplayName("userId 로 모든 포스트를 조회하는 것을 성공한다.")
    void getAllPostsByUserId() {
        List<Post> list = new ArrayList<>();
        list.add(createPost());
        list.add(createPost());
        Page<Post> page = new PageImpl<>(list);
        int pageNum = 10;
        PageRequest pageRequest = PageRequest.of(pageNum, 5, Sort.by(DESC, "createdAt"));
        Mockito.when(postRepository.findPostsByUserId(1L, pageRequest)).thenReturn(page);

        Page<PostResponseDto> posts = sut.getAllPostsByUserId(1L, pageNum);

        assertThat(posts.getTotalElements()).isEqualTo(2);
    }

    @Test
    @DisplayName("존재하지 않는 포스트 변경을 하려고 하면 실패한다.")
    void edit() {
        PostEditRequestDto requestDto = PostEditRequestDto.builder()
                .postId(1L)
                .title("변경")
                .content("변경내용")
                .build();

        assertThatThrownBy(() -> sut.edit(requestDto)).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("존재하지 않는 포스트를 삭제하려고 하면 실패한다.")
    void deletePost() {
        assertThatThrownBy(() -> sut.deletePost(2L)).isInstanceOf(NotFoundException.class);
    }


//    팩토리 메서드로 멤버
    private User createMember(Long userId){
        User user = new User("Seyeon", 20, "coding", userId);

       return user;
    }

    private Post createPost(){
        User member = createMember(1L);
        return new Post("제목", "내용", member);
    }

    private static PostCreateRequestDto createPostDto(Post post, User member){
        return PostCreateRequestDto.builder()
                .userId(member.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}