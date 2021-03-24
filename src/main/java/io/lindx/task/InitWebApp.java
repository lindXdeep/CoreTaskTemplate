package io.lindx.task;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import io.lindx.task.config.SecurityConfig;
import io.lindx.task.config.WebMvcConfig;

/**
 * Implementation of
 * {@link AbstractAnnotationConfigDispatcherServletInitializer} interface.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Order(1)
public class InitWebApp extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Метод, указывающий на класс конфигурации.
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {};
  }

  /**
   * Добавление конфигурации, в которой инициализируем ViewResolver, для
   * корректного отображения.
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebMvcConfig.class, SecurityConfig.class };
  }

  /**
   * Данный метод указывает url, на котором будет базироваться приложение.
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] { "/*" };
  }

  /* Перенаправление входящих HTTP-запросов на нужные методы контроллера */
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {

    super.onStartup(servletContext);

    servletContext

        .addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter())

        .addMappingForUrlPatterns(null, true, "/*");
  }
}
