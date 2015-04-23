package ch.erni.community.ideasboard.backend.configuration;

import ch.erni.community.ideasboard.backend.security.SecurityConstantsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Configuration
public class OAuthServerConfiguration {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {

		@Autowired
		private SecurityConstantsLoader securityConstantsLoader;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources
					.resourceId(securityConstantsLoader.getResourceId());
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.antMatchers("/ideas/**").hasAuthority("ROLE_USER")
					.anyRequest().permitAll()

					.and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/")
					.permitAll();
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		final Logger logger = Logger.getLogger(OAuthServerConfiguration.class.getName());

		// TODO @rap: This should change in the future for something more permanent
		private TokenStore tokenStore = new InMemoryTokenStore();

		@Autowired
		private SecurityConstantsLoader securityConstantsLoader;

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints
					.tokenStore(this.tokenStore)
					.authenticationManager(this.authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			logger.log(Level.INFO, "Configuring resource server...");
			clients
					.inMemory()
					.withClient(securityConstantsLoader.getClientId())
					.authorizedGrantTypes("password", "refresh_token")
					.authorities("ROLE_USER")
					.scopes("read", "write")
					.resourceIds(securityConstantsLoader.getResourceId())
					.secret(securityConstantsLoader.getClientSecret());
		}

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(this.tokenStore);
			return tokenServices;
		}

	}

}
