package jm.task.core.hiber.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * WebAppInitializer
 */
public class WebAppInitializer /*
								 * extends
								 * AbstractAnnotationConfigDispatcherServletInitializer
								 */ {

	// Метод, указывающий на класс конфигурации

	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {};
	}

	// Добавление конфигурации, в которой инициализируем ViewResolver, для
	// корректного отображения jsp.

	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	/* Данный метод указывает url, на котором будет базироваться приложение */

	protected String[] getServletMappings() {
		return new String[] { "/*" };
	}

	/* Перенаправление входящих HTTP-запросов на нужные методы контроллера */

	public void onStartup(ServletContext servletContext) throws ServletException {
		// super.onStartup(servletContext);
		registerHiddenFieldFilter(servletContext);
	}

	private void registerHiddenFieldFilter(ServletContext servletContext) {
		servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null,
				true, "/*");
	}

}