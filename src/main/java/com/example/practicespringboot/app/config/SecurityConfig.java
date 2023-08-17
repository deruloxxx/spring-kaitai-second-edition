package com.example.practicespringboot.app.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
        .usernameParameter("userId")
        .passwordParameter("password")
        .defaultSuccessUrl("/user/list", true) // ログイン成功後のリダイレクト先URL
      )
      .logout(logout -> logout
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout")
      )
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .requestMatchers(PathRequest.toH2Console()).permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/user/signup").permitAll()
        .requestMatchers("/user/signup/rest").permitAll()
        .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
        .anyRequest().authenticated()
      );
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
      .username("user")
      .password("password")
      .roles("USER")
      .build();
    return new InMemoryUserDetailsManager(user);
  }
}