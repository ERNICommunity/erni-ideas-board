package ch.erni.community.ideasboard.backend.security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author rap
 */
public class SecurityConstantsLoaderTest {

	private SecurityConstantsLoader securityConstantsLoader = new SecurityConstantsLoader();

	@Test
	public void testGetResourceId() throws Exception {
		assertEquals("/", securityConstantsLoader.getResourceId());
	}

	@Test
	public void testGetClientSecret() throws Exception {
		assertEquals("TEST", securityConstantsLoader.getClientSecret());
	}

	@Test
	public void testGetClientId() throws Exception {
		assertEquals("TESTID", securityConstantsLoader.getClientId());
	}
}