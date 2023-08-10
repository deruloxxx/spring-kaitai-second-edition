package com.example.practicespringboot.app.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
// Chore SecurityConfigについてちゃんと学習する
public class SecurityConfig {
  @Bean
  public org.springframework.security.web.SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .loginProcessingUrl("/login") // フォーム認証の設定記述開始
        .loginPage("/login") // ユーザー名・パスワードの送信先URL
        .defaultSuccessUrl("/") // ログイン成功後のリダイレクト先URL
        .failureUrl("/error") // ログイン失敗後のリダイレクト先URL
        .permitAll() // ログイン画面は未ログインでもアクセス可能
      )
      .logout(logout -> logout
        .logoutSuccessUrl("/")
      )
      .authorizeHttpRequests(authz -> authz
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .requestMatchers(PathRequest.toH2Console()).permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/user/signup").permitAll()
        .anyRequest().authenticated()
      );
    // CSRF対策を無効に設定(一時的)
    return http.build();
  }
}