package io.lindx.task.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Implementation of {@link AbstractAnnotationConfigDispatcherServletInitializer}
 * interface.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebAppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/*" };
	}

}
