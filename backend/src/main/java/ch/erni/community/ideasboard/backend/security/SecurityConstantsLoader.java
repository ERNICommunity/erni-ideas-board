package ch.erni.community.ideasboard.backend.security;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author rap
 */
@Component
public class SecurityConstantsLoader {

	public static final String SECURITY_PROPERTIES = "/security.properties";

	public static final String REST_RESOURCE_ID = "rest.resource.id";

	public static final String OAUTH_CLIENT_SECRET = "oauth.client.secret";

	public static final String OAUTH_CLIENT_ID = "oauth.client.id";

	private Properties properties;

	public SecurityConstantsLoader() {
		properties = new Properties();
		try (InputStream is = getClass().getResourceAsStream(SECURITY_PROPERTIES)) {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getProperty(String key) {
		return (String) properties.get(key);
	}

	public String getResourceId() {
		return getProperty(REST_RESOURCE_ID);
	}

	public String getClientSecret() {
		return getProperty(OAUTH_CLIENT_SECRET);
	}

	public String getClientId() {
		return getProperty(OAUTH_CLIENT_ID);
	}

}
