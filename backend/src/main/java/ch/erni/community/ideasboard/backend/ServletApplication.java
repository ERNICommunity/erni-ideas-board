package ch.erni.community.ideasboard.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This is a main class for the deployment to the standalone Tomcat (or other) servlet container.
 * To enable this class choose `package-war` maven profile.
 *
 * @author rap
 */
@SpringBootApplication
public class ServletApplication extends SpringBootServletInitializer {

	private static Class<Application> applicationClass = Application.class;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}

