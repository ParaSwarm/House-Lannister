package edu.uco.houselannister.saveasingle.model;

/**
 * Created by Gordon on 9/20/2016.
 */
public interface IAuthentication {

    void Authenticate(String username, String password);

    Boolean IsUser();

    Boolean IsAdmin();
}
