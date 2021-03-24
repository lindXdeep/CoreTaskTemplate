package io.lindx.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
      .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/users/sign_up").permitAll()
        .antMatchers("/users/").hasAnyRole("ROLE_ADMIN")
        .anyRequest().authenticated();
      
    http
      .formLogin()
        .loginPage("/login").permitAll()
        .loginProcessingUrl("/login/process")
        .usernameParameter("email")
        .passwordParameter("password");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth
      .inMemoryAuthentication()

        .withUser("admin@admin")
        .password("admin")
        .roles("ADMIN")

      .and()

        .withUser("user@user")
        .password("user")
        .roles("USER");
  }

  @Bean
  public static NoOpPasswordEncoder passwordEncoder() {
      return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
  }
}