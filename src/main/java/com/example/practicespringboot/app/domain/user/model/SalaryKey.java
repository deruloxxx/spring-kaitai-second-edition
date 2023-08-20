package com.example.practicespringboot.app.domain.user.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SalaryKey implements Serializable {
  private String userId;
  private String yearMonth;
}
