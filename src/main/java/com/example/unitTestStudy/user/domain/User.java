package com.example.unitTestStudy.user.domain;

import com.example.unitTestStudy.common.BaseEntity;
import com.example.unitTestStudy.post.domain.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class User extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, length = 20, unique = true)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "hobby")
    private String hobby;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User(Long id, String name, int age, String hobby) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public void changeHobby(String hobby) {
        this.hobby = hobby;
    }

}