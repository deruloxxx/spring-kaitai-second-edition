package com.example.practicespringboot.app.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// Chore SecurityConfigについてちゃんと学習する
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .loginProcessingUrl("/login") // フォーム認証の設定記述開始
        .loginPage("/login") // ユーザー名・パスワードの送信先URL
        .failureUrl("/login?error") // ログイン失敗後のリダイレクト先URL
        .defaultSuccessUrl("/user/list", true) // ログイン成功後のリダイレクト先URL
        .permitAll() // ログイン画面は未ログインでもアクセス可能
      )
      .logout(logout -> logout
        .logoutSuccessUrl("/")
      )
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .requestMatchers(PathRequest.toH2Console()).permitAll()
        .requestMatchers("/general").hasRole("GENERAL")
        .requestMatchers("/admin").hasRole("ADMIN")
        .requestMatchers("/user/signup").permitAll()
        .anyRequest().authenticated()
      );
    return http.build();
  }
}