package io.lindx.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

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

	private final ApplicationContext applicationContext;

	@Autowired
	public WebAppConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return ResourceTemplateResolver.
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}

	/**
	 * @return SpringTemplateEngine.
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	/**
	 * ThymeleafViewResolver.
	 */
	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);
	}

}
