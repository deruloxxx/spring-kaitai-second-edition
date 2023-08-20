package com.example.practicespringboot.app.repository;

import com.example.practicespringboot.app.domain.user.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MUser, String> {
}
