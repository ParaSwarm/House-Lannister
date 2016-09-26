package edu.uco.houselannister.saveasingle.domain;

public interface Authentication {

    void Authenticate(String email, String password);

    Boolean isUser();

    Boolean isAdmin();

    User getAuthenticatedUser();
}
