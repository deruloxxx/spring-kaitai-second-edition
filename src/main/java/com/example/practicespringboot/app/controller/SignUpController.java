package com.example.practicespringboot.app.controller;

import com.example.practicespringboot.app.domain.user.model.MUser;
import com.example.practicespringboot.app.domain.user.service.UserService;
import com.example.practicespringboot.app.form.GroupOrder;
import com.example.practicespringboot.app.form.SignUpForm;
import com.example.practicespringboot.app.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignUpController {
  @Autowired
  private UserApplicationService userApplicationService;
  @Autowired
  private UserService userService;
  @Autowired
  private ModelMapper modelMapper;
  @GetMapping("/signup")
  public String getSignup(Model model, Locale locale, @ModelAttribute SignUpForm form) {
    Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
    model.addAttribute("genderMap", genderMap);
    return "user/signup";
  }
  @PostMapping("/signup")
  // @Validatedアノテーションをクラスに付けると、バリデーションが実行されます。
  public String postSignup(Model model, Locale locale, @ModelAttribute @Validated(GroupOrder.class) SignUpForm form, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return getSignup(model, locale, form);
    }
    log.info(form.toString());
    MUser user = modelMapper.map(form, MUser.class);
    userService.signup(user);
    return "redirect:/login";
  }
  @ExceptionHandler(DataAccessException.class)
  public String dataAccessExceptionHandler(DataAccessException e, Model model) {
    model.addAttribute("error", "");
    model.addAttribute("message", "SignUpControllerで例外が発生しました");
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
    return "error";
  }
  @ExceptionHandler(Exception.class)
  public String exceptionHandler(Exception e, Model model) {
    model.addAttribute("error", "");
    model.addAttribute("message", "SignUpControllerで例外が発生しました");
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
    return "error";
  }

}
