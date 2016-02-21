package ie.kevinmay.config.core;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ie.kevinmay.config.MvcConfiguration;

public class SpringMvcInitializer 
       extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MvcConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
}