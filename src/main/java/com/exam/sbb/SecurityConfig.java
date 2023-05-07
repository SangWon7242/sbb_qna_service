package com.exam.sbb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  @Autowired
  private OAuth2UserService oAuth2UserService;
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests().requestMatchers(
        new AntPathRequestMatcher("/**")).permitAll()
        .and() // 문맥의 끝
        .csrf().ignoringRequestMatchers(
            new AntPathRequestMatcher("/h2-console/**"))
        .and()
        .headers()
        .addHeaderWriter(new XFrameOptionsHeaderWriter(
            XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
        .and()
        .formLogin()
        .loginPage("/user/login")
        .defaultSuccessUrl("/")
        .and()
        .oauth2Login(
            oauth2Login -> oauth2Login
                .loginPage("/usr/login")
                .userInfoEndpoint(
                    userInfoEndpoint -> userInfoEndpoint
                        .userService(oAuth2UserService)
                )
        )
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true);

    return http.build();
  }

  // 스프링 시스템에 객체를 등록한다.
  // @Configuration 라는 어노테이션을 가진 클래스에서만 사용가능하다.
  @Bean // @Bean : 스프링이 관리하는 객체
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
