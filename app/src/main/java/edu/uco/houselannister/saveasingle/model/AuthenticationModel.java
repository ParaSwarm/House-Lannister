package edu.uco.houselannister.saveasingle.model;

import edu.uco.houselannister.saveasingle.domain.Authentication;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by Gordon on 9/25/2016.
 */
public class AuthenticationModel implements Authentication {

    private ServiceProxy proxy;

    private static Authentication authenticationInstance = null;

    private AuthenticationModel(ServiceProxy proxy) {
        this.proxy = proxy;
    }

    public static Authentication getAuthenticationInstance(ServiceProxy proxy) {
        if (authenticationInstance == null)
            authenticationInstance = new AuthenticationModel(proxy);
        return authenticationInstance;
    }

    @Override
    public void Authenticate(String email, String password) {
        proxy.Authenticate(email, password);
    }

    @Override
    public Boolean isUser() {
        return this.proxy.getAuthenticatedUser() != null;
    }

    @Override
    public Boolean isAdmin() {
        if (this.proxy.getAuthenticatedUser() != null) {
            return this.proxy.getAuthenticatedUser().getAdmin();
        }
        return false;
    }

    @Override
    public User getAuthenticatedUser() {
        return this.proxy.getAuthenticatedUser();
    }

    @Override
    public User getCurrentUser() {
        return this.proxy.getCurrentUser();
    }

    @Override
    public void setCurrentUserImpersonation(User user) {
        this.proxy.setCurrentUserImpersonation(user);
    }

    @Override
    public void resetCurrentUserImpersonation() {
        this.proxy.resetCurrentUserImpersonation();
    }

}
