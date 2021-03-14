package io.lindx.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.lindx.task.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("io.lindx.task.security")
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthProviderImpl authProvider;

	/**
	 * В этом методе описывается разделение доступа к хэндлерам.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
        .authorizeRequests()
            .antMatchers("/sign_in").anonymous()
            .antMatchers("/users").authenticated()

				.and().csrf().disable()

				.formLogin()
            .loginPage("/sign_in")
            .loginProcessingUrl("/login/process")
            .usernameParameter("email")
				    
        .and()
              .exceptionHandling().accessDeniedPage("/")
				.and()
			    	.logout();
	}

	/**
	 * Builder Authentication Provider.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
    /**
   * @return BCryptPasswordEncoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
