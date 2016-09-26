package edu.uco.houselannister.saveasingle.domain;

public interface Authentication {

    void Authenticate(String username, String password);

    Boolean isUser();

    Boolean isAdmin();
}
