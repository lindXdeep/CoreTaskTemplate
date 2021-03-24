package io.lindx.task.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
          .antMatchers("/img/**", "/css/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
    http
      . ()
        .antMatchers("/").permitAll()
        .antMatchers("/users/sign_up").permitAll()
        .anyRequest().authenticated();
      
    http
      .formLogin()
        .loginPage("/login").permitAll()
        .loginProcessingUrl("/login/process")
        .usernameParameter("email")
        .passwordParameter("password");
  }
}