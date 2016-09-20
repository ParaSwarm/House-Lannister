package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Gordon on 9/20/2016.
 */
public class AppModel implements IAuthentication, IPreferences, IUserProfile, IAppModel {

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

    //region Implementation if IAuthentication
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
    //endregion Implementation if IAuthentication

    //region Implementation of IPreferences
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
    //endregion Implementation of IPreferences

    @Override
    public User GetUser(String username) {
        return proxy.GetUser(username);
    }

    @Override
    public void saveCurrentUser() {
        this.proxy.SaveUser(currentUser);
    }


    //region Implementation of IAppModel
    @Override
    public User getCurrentUser() {
        return currentUser;
    }
    //endregion


}
