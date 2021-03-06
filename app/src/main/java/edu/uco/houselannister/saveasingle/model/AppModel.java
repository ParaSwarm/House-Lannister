package edu.uco.houselannister.saveasingle.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import edu.uco.houselannister.saveasingle.domain.*;

public class AppModel implements Model {

    //region Implementation of Singleton Pattern for Model
    private static Model appModelInstance;

    private ServiceProxy proxy;

    private AppModel(ServiceProxy proxy) {
        this.proxy = proxy;
    }

    public static Model getAppModelInstance(ServiceProxy proxy) {
        if (appModelInstance == null) {
            appModelInstance = new AppModel(proxy);
        }
        return appModelInstance;
    }
    //endregion Implementation of Singleton Pattern for Model

    //region Implementation of Authentication
    @Override
    public void Authenticate(String email, String password) {
        AuthenticationModel.getAuthenticationInstance(proxy).Authenticate(email, password);
    }

    @Override
    public Boolean isUser() {
        return AuthenticationModel.getAuthenticationInstance(proxy).isUser();
    }

    @Override
    public Boolean isAdmin() {
        return AuthenticationModel.getAuthenticationInstance(proxy).isAdmin();
    }

    @Override
    public User getAuthenticatedUser() {
        return AuthenticationModel.getAuthenticationInstance(proxy).getAuthenticatedUser();
    }

    @Override
    public User getCurrentUser() {
        return AuthenticationModel.getAuthenticationInstance(proxy).getCurrentUser();
    }

    @Override
    public void setCurrentUserImpersonation(User user) {
        AuthenticationModel.getAuthenticationInstance(proxy).setCurrentUserImpersonation(user);
    }

    @Override
    public void resetCurrentUserImpersonation() {
        AuthenticationModel.getAuthenticationInstance(proxy).resetCurrentUserImpersonation();
    }

    //endregion Implementation of Authentication

    //region Implementation of Preferences
    @Override
    public Questionnaire getQuestionnaire() {
        return PreferenceModel.getPreferencesInstance(proxy).getQuestionnaire();
    }

    @Override
    public ArrayList<Response> getUserResponses(String username) {
        return PreferenceModel.getPreferencesInstance(proxy).getUserResponses(username);
    }

    @Override
    public Response getUserResponse(String username, Question question) {
        return PreferenceModel.getPreferencesInstance(proxy).getUserResponse(username, question);
    }
    //endregion Implementation of Preferences

    //region Implementation of User Profile Interface.
    @Override
    public User getUser(String username) {
        return UserProfileModel.getUserProfileInstance(proxy).getUser(username);
    }

    @Override
    public void saveUser(User user) {
        UserProfileModel.getUserProfileInstance(proxy).saveUser(user);
    }

    @Override
    public  void deleteUser(User user){
        UserProfileModel.getUserProfileInstance(proxy).deleteUser(user);
    }
    //endregion Implementation of User Profile Interface.

    //region Implementation of Model Specialized methods

    @Override
    public ArrayList<User> getUsers() {
        return proxy.getUsers();
    }

    @Override
    public ArrayList<String> getUsernameArray() {
        return proxy.getUsernameArray();
    }

    @Override
    public ArrayList<HashMap<String, String>> getUsernameMap() {
        return proxy.getUsernameMap();
    }

    @Override
    public void saveQuestion(Question question) {
        proxy.saveQuestion(question);
    }

    //endregion Implementation of Model Specialized methods
}
