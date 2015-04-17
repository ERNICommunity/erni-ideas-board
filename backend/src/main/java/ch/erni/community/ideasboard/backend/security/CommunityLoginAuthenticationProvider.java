package ch.erni.community.ideasboard.backend.security;

import ch.erni.community.ideasboard.backend.service.CommunityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Component
public class CommunityLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CommunityUserService communityUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return communityUserService.authenticate((String) authentication.getPrincipal(), authentication.getCredentials().toString());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
