package com.example.practicespringboot.app.service.impl;

import com.example.practicespringboot.app.domain.user.model.MUser;
import com.example.practicespringboot.app.domain.user.service.UserService;
import com.example.practicespringboot.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl2 implements UserService {
  @Autowired
  private UserRepository repository;

  @Autowired
  private PasswordEncoder encoder;

  @Transactional
  @Override
  public void signup(MUser user) {
    boolean exists = repository.existsById(user.getUserId());
    if (exists) {
      throw new DataAccessException("ユーザーがすでに存在") {};
    }

    user.setDepartmentId(1);
    user.setRole("ROLE_GENERAL");

    String rawPassword = user.getPassword();
    user.setPassword(encoder.encode(rawPassword));

    repository.save(user);
  }

  @Override
  public List<MUser> getUsers(MUser user) {
    return repository.findAll();
  }

  @Override
  public MUser getUserOne(String userId) {
    Optional<MUser> option = repository.findById(userId);
    MUser user = option.orElse(null);
    return user;
  }

  @Transactional
  @Override
  public void updateUserOne(String userId, String password, String userName) {}

  @Transactional
  @Override
  public void deleteUserOne(String userId) {
    repository.deleteById(userId);
  }

  @Override
  public MUser getLoginUser(String userId) {
    Optional<MUser> option = repository.findById(userId);
    MUser user = option.orElse(null);
    return user;
  }
}
