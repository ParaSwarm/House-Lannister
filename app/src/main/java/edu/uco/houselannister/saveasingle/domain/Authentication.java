package edu.uco.houselannister.saveasingle.domain;

public interface Authentication  {

    void Authenticate(String email, String password);

    Boolean isUser();

    Boolean isAdmin();

    /*
    * Authenticated user is the currently logged in user.
    * The impersonated user is the user interacting with the application.
    * Most of the time, the authenticated user and the impersonated user
    * are the same.
    *
    * In Administrative cases, the admin can impersonate a user and use
    * the application as the user.  This is for modifications and corrections
    * that the admins may need to do to fix problems.
    * */

    User getAuthenticatedUser();

    User getCurrentUser();

    void setCurrentUserImpersonation(User user);

    void resetCurrentUserImpersonation();

}
