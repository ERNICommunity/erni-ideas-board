package ch.erni.community.ideasboard.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Default application runner. It will run Spring Boot application inside embedded Tomcat.
 * This is activated (by default) by the `package-jar` maven profile.
 *
 * @author rap
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
