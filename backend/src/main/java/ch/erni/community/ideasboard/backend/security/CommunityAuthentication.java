package ch.erni.community.ideasboard.backend.security;

import ch.erni.community.ideasboard.backend.model.IdeasBoardUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class CommunityAuthentication implements Authentication {

    private boolean authenticated;

    private IdeasBoardUser ideasBoardUser;

    public CommunityAuthentication(boolean statusResponse, IdeasBoardUser ideasBoardUser) {
        this.authenticated = statusResponse;
        this.ideasBoardUser = ideasBoardUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "USER");
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return ideasBoardUser.getEmail();
    }

    @Override
    public Object getPrincipal() {
        return ideasBoardUser;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        authenticated = b;
    }

    @Override
    public String getName() {
        return ideasBoardUser.getEmail();
    }
}
