package com.example.practicespringboot.app.repository.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RestResult {
  private int result;
  private Map<String, String> errors;
}
