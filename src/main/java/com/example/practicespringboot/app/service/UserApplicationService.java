package com.example.practicespringboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class UserApplicationService {
  @Autowired
  private MessageSource messageSource;
  public Map<String, Integer> getGenderMap(Locale locale) {
    Map<String, Integer> genderMap = new LinkedHashMap<>();
    String male = messageSource.getMessage("male", null, locale);
    String female = messageSource.getMessage("female", null, locale);
    genderMap.put("男性", 1);
    genderMap.put("女性", 2);
    return genderMap;
  }
}
