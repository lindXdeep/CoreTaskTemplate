package io.lindx.task.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration implements {@link WebMvcConfigurer}
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "io.lindx.task.controller")
public class WebAppConfig implements WebMvcConfigurer {

}
