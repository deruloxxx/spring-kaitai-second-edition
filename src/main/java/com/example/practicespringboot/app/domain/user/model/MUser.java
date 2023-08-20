package com.example.practicespringboot.app.domain.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "m_user")
public class MUser {
  @Id
  private String userId;
  private String password;
  private String userName;
  private Date birthday;
  private Integer age;
  private Integer gender;
  private Integer departmentId;
  private String role;

  @ManyToOne(optional = true)
  @JoinColumn(insertable = false, updatable = false, name = "departmentId")
  @Transient
  private Department department;
  @Transient
  private List<Salary> salaryList;
}
