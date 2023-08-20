package com.example.practicespringboot.app.repository.rest;

import com.example.practicespringboot.app.domain.user.model.MUser;
import com.example.practicespringboot.app.domain.user.service.UserService;
import com.example.practicespringboot.app.form.GroupOrder;
import com.example.practicespringboot.app.form.SignUpForm;
import com.example.practicespringboot.app.form.UserDetailForm;
import com.example.practicespringboot.app.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {
  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/get/list")
  public List<MUser> getUserList(UserListForm form) {
    MUser user = modelMapper.map(form, MUser.class);

    List<MUser> userList = userService.getUsers(user);

    return userList;
  }

  @PostMapping("/signup/rest")
  public RestResult postSignup(@Validated(GroupOrder.class)SignUpForm form, BindingResult bindingResult, Locale locale) {
    if(bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();

      for (FieldError error: bindingResult.getFieldErrors()) {
        String message = messageSource.getMessage(error, locale);
        errors.put(error.getField(), message);
      }
      // 90の意味は'バリデーション結果NG'
      return new RestResult(90, errors);
    }

    MUser user = modelMapper.map(form, MUser.class);
    userService.signup(user);

    return new RestResult(0, null);
  }

  @PutMapping("/update")
  public int updateUser(UserDetailForm form) {
    userService.updateUserOne(
      form.getUserId(),
      form.getPassword(),
      form.getUserName()
    );
    return 0;
  }

  @DeleteMapping("/delete")
  public int deleteUser(UserDetailForm form) {
    userService.deleteUserOne(form.getUserId());

    return 0;
  }

}
