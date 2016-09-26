package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.uco.houselannister.saveasingle.domain.*;

public class AppModel implements Model {

    private static Model appModelInstance;

    private ServiceProxy proxy;

    //region Implementation of Singleton Pattern for Model
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

    //endregion Implementation of Authentication

    //region Implementation of Preferences
    @Override
    public Questionnaire getQuestionnaire() {
        return PreferenceModel.getPreferencesInstance(proxy).getQuestionnaire();
    }

    @Override
    public ArrayList<Response> getUserResponses(String username) {
        return this.proxy.GetUserResponses(username);
    }

    @Override
    public Response getUserResponse(String username, Question question) {
        return this.proxy.GetUserResponse(username, question);
    }
    //endregion Implementation of Preferences

    //region Implementation of User Profile Interface.
    @Override
    public User GetUser(String username) {
        return proxy.GetUser(username);
    }

    @Override
    public void saveCurrentUser() {
        this.proxy.SaveUser(getAuthenticatedUser());
    }
    //endregion Implementation of User Profile Interface.

    //region Implementation of Model

    @Override
    public ArrayList<User> getUsers() {
        return proxy.getUsers();
    }

    @Override
    public ArrayList<String> getUsernameArray() {
        ArrayList<String> ret = new ArrayList<>();
        for (User u : proxy.getUsers()) {
            ret.add(u.getName() + " - " + u.getEmailAddress());
        }
        return ret;
    }

    @Override
    public ArrayList<HashMap<String, String>> getUsernameMap() {

        ArrayList<HashMap<String, String>> ret = new ArrayList<>();


        for (User u : proxy.getUsers()) {
            HashMap<String, String> item = new HashMap<>();
            item.put(u.getName(), u.getEmailAddress());
            ret.add(item);
        }

        return ret;
    }

    //endregion
}
