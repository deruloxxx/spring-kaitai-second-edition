package com.example.practicespringboot.app.domain.user.service.impl;

import com.example.practicespringboot.app.domain.user.model.MUser;
import com.example.practicespringboot.app.domain.user.service.UserService;
import com.example.practicespringboot.app.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper mapper;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public void signup(MUser user) {
    user.setDepartmentId(1);
    user.setRole("ROLE_GENERAL");

    String rawPassword = user.getPassword();
    user.setPassword(encoder.encode(rawPassword));
    mapper.insertOne(user);
  }
  @Override
  public List<MUser> getUsers(MUser user) {
    return mapper.findMany(user);
  }
  @Override
  public MUser getUserOne(String userId) {
    return mapper.findOne(userId);
  }
  @Transactional
  @Override
  public void updateUserOne(String userId, String password, String userName) {
    String encryptPassword = encoder.encode(password);
    mapper.updateOne(userId, encryptPassword, userName);
//    int i = 1 / 0;
  }
  @Override
  public void deleteUserOne(String userId) {
    int count = mapper.deleteOne(userId);
  }
}
