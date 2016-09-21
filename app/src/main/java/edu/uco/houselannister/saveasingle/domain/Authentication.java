package edu.uco.houselannister.saveasingle.domain;

/**
 * Created by Gordon on 9/20/2016.
 */
public interface Authentication {

    void Authenticate(String username, String password);

    Boolean IsUser();

    Boolean IsAdmin();
}
