package io.lindx.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.lindx.task.security.SuccessUserHandler;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserDetailsService userDetailsService;
  private SuccessUserHandler successUserHandler;
=
    this.successUserHandler = successUserHandler;
  }
  

  @Override
  public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
          .antMatchers("/img/**", "/css/**")
          .antMatchers("/setadmin");          // set default admin/admin in db
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
    http
      .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/login").anonymous()
        .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
        .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        .anyRequest().authenticated();
      
    http
      .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login/process")
        .usernameParameter("email")
        .passwordParameter("password")
        .failureUrl("/login?error=true")
        .defaultSuccessUrl("/", true)
        .successHandler(successUserHandler);

    http
      .logout()
        .logoutUrl("/logout");

    http
      .exceptionHandling()
        .accessDeniedPage("/");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
  }
}