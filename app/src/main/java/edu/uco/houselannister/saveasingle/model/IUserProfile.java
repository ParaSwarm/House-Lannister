package edu.uco.houselannister.saveasingle.model;

/**
 * Created by Gordon on 9/20/2016.
 * This interface provides access methods
 * to the users profile
 */
public interface IUserProfile {

    User GetUser(String username );

    void saveCurrentUser();
}
