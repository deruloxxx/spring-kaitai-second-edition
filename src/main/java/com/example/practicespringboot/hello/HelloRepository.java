package com.example.practicespringboot.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

// DB操作を表す
@Repository
public class HelloRepository {

  // jdbcTemplateを使用してSQLを実行
  @Autowired
  public JdbcTemplate jdbcTemplate;
  public Map<String, Object> findById(String id) {
    String query = "SELECT *" + " FROM employee" + " WHERE id=?";
    Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);
    return employee;
  }
}
