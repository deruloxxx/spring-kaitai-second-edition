package com.example.practicespringboot.app.domain.user.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_salary")
public class Salary {
  //  private String userId;
//  private String yearMonth;
  @EmbeddedId
  private SalaryKey salaryKey;
  private Integer salary;
}
