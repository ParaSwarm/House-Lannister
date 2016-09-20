package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Gordon on 9/20/2016.
 */
public class AppModel implements IAuthentication, IQuestionnaire, IUserProfile {

    private IServiceProxy proxy;

    private User currentUser;

    private Boolean isAuthenticated;

    private Questionnaire questionnaire;

    private AppModel(IServiceProxy proxy) {
        this.proxy = proxy;
    }

    public static AppModel createAppModel(IServiceProxy proxy) {
        return new AppModel(proxy);
    }

    @Override
    public void Authenticate(String username, String password) {
        User u = proxy.GetUser(username);
        this.isAuthenticated = Objects.equals(u.getPassword(), password);
    }

    @Override
    public Boolean IsUser() {
        if (getCurrentUser() != null) {
            return this.isAuthenticated;
        }
        return false;
    }

    @Override
    public Boolean IsAdmin() {
        if (getCurrentUser() != null) {
            return getCurrentUser().getAdmin();
        }
        return false;
    }

    @Override
    public Questionnaire GetQuestionnaire(String username) {
        this.questionnaire = proxy.GetQuestionnaire();
        return this.questionnaire;
    }

    @Override
    public ArrayList<Response> GetUserResponses(String username) {
        return this.proxy.GetUserResponses(username);
    }

    @Override
    public Response GetUserResponse(String username, Question question) {
        return this.proxy.GetUserResponse(username, question);
    }

    @Override
    public User GetUser(String username) {
        return proxy.GetUser(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void saveCurrentUser() {
        this.proxy.SaveUser(currentUser);
    }
}
