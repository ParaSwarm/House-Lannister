package edu.uco.houselannister.saveasingle.model;

import edu.uco.houselannister.saveasingle.domain.Authentication;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by Gordon on 9/25/2016.
 */
public class AuthenticationModel implements Authentication {

    private User currentUser;
    private Boolean isAuthenticated;
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

        this.isAuthenticated = false;

        for (User u : this.proxy.getUsers()) {
            this.isAuthenticated = email.toLowerCase().equals(u.getEmailAddress().toLowerCase()) && password.toLowerCase().equals(u.getPassword().toLowerCase());
            if (isAuthenticated) {
                this.currentUser = u;
                break;
            }
        }
    }

    @Override
    public Boolean isUser() {
        if (this.currentUser != null) {
            return this.isAuthenticated;
        }
        return false;
    }

    @Override
    public Boolean isAdmin() {
        if (currentUser != null) {
            return currentUser.getAdmin();
        }
        return false;
    }
}
