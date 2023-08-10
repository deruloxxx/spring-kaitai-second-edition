package com.example.practicespringboot.app.form;

import com.example.practicespringboot.app.domain.user.model.Department;
import com.example.practicespringboot.app.domain.user.model.Salary;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDetailForm {
  private String userId;
  private String password;
  private String userName;
  private Date birthday;
  private Integer age;
  private Integer gender;
  private Department department;
  private List<Salary> salaryList;
}
