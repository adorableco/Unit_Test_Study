package com.example.unitTestStudy.user.repository;

import com.example.unitTestStudy.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}