package com.example.unitTestStudy.Post;

import com.example.unitTestStudy.post.converter.PostConverter;
import com.example.unitTestStudy.post.domain.Post;
import com.example.unitTestStudy.post.dto.PostCreateRequestDto;
import com.example.unitTestStudy.post.dto.PostEditRequestDto;
import com.example.unitTestStudy.post.dto.PostResponseDto;
import com.example.unitTestStudy.post.repository.PostRepository;
import com.example.unitTestStudy.post.service.PostService;
import com.example.unitTestStudy.user.domain.User;
import com.example.unitTestStudy.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith({MockitoExtension.class})
public class PostServiceTest {
    @InjectMocks
    private PostService sut;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Spy
    private PostConverter postConverter;

    @Test
    void Save_Post(){
        User user = createUserEntity();
        Post post = createPostEntity();
        PostCreateRequestDto postDto = createPostDto(post);
        Mockito.when(userRepository.findById(postDto.getUserId())).thenReturn(Optional.of(user));
        Mockito.when(postRepository.save(any())).thenReturn(post);

        Long savedPostId = sut.save(postDto);

        Assertions.assertThat(savedPostId).isEqualTo(post.getId());
    }

    @Test
    void get_Post_by_postId(){
        Post post = createPostEntity();
        Mockito.when(postRepository.findById(any())).thenReturn(Optional.of(post));

        PostResponseDto receivedPost = sut.getPost(post.getId());

        Assertions.assertThat(receivedPost.getId()).isEqualTo(post.getId());
    }

    @Test
    void get_All_post(){

    }

    @Test
    void get_All_Posts_By_UserId(){

    }

    @Test
    void edit_Post(){
        Post post = createPostEntity();
        PostEditRequestDto editDto = PostEditRequestDto.builder()
                .title("editedTitle")
                .content("editedContent")
                .postId(post.getId())
                .build();
        Mockito.when(postRepository.findById(any())).thenReturn(Optional.of(post));

        sut.edit(editDto);

        Assertions.assertThat("editedTitle").isEqualTo(post.getTitle());
        Assertions.assertThat("editedContent").isEqualTo(post.getContent());
    }

    @Test
    void delete_Post(){

    }

    private User createUserEntity(){
        return new User("Sunyoung", 23, "hobby");
    }

    private Post createPostEntity(){
        User user = createUserEntity();
        return new Post("제목이다.", "내용이다.", user);
    }

    private PostCreateRequestDto createPostDto(Post post){
        return PostCreateRequestDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getId())
                .build();
    }
}
