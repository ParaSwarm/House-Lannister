package edu.uco.houselannister.saveasingle.service;
import java.util.ArrayList;
import java.util.HashMap;

import edu.uco.houselannister.saveasingle.domain.*;


public class AppService implements ServiceProxy {

    private User currentUser;
    private Boolean isAuthenticated;
    private Questionnaire questionnaire;
    private ArrayList<User> users;
    private ArrayList<Message> messages;

    //region Implementation of Singleton Pattern for Creation
    private static ServiceProxy appServiceInstance = null;

    private AppService() {
    }

    public static ServiceProxy getAppServiceInstance() {
        if (appServiceInstance == null) {
            appServiceInstance = new AppService();
        }
        return appServiceInstance;
    }

    //endregion Implementation of Singleton Pattern for Creation

    //region Implementation of Service Model Interface
    @Override
    public ArrayList<User> getUsers() {
        if (users == null)
            users = StaticUserModel.getUsers();
        return users;
    }

    @Override
    public ArrayList<String> getUsernameArray() {
        ArrayList<String> ret = new ArrayList<>();
        for (User u : getUsers()) {
            ret.add(u.getName() + " - " + u.getEmailAddress());
        }
        return ret;
    }

    @Override
    public ArrayList<HashMap<String, String>> getUsernameMap() {
        ArrayList<HashMap<String, String>> ret = new ArrayList<>();

        for (User u : getUsers()) {
            HashMap<String, String> item = new HashMap<>();
            item.put(u.getName(), u.getEmailAddress());
            ret.add(item);
        }

        return ret;
    }

    @Override
    public ArrayList<Message> getInboxMessages() {
        if (messages == null)
            messages = StaticUserModel.getMessages();
        return messages;
    }
    //endregion Implementation of Service Model Interface

    //region Implementation of Service Authentication Methods
    @Override
    public void Authenticate(String email, String password) {
        isAuthenticated = false;
        currentUser = null;
        for (User u : getUsers()) {
            this.isAuthenticated = email.toLowerCase().equals(u.getEmailAddress().toLowerCase()) && password.toLowerCase().equals(u.getPassword().toLowerCase());
            if (isAuthenticated) {
                this.currentUser = u;
                break;
            }
        }
    }

    @Override
    public Boolean isUser() {
        return this.currentUser != null;
    }

    @Override
    public Boolean isAdmin() {
        if (this.currentUser != null) return this.currentUser.getAdmin();
        return false;
    }

    @Override
    public User getAuthenticatedUser() {
        return currentUser;
    }
    //endregion Implementation of Service Authentication Methods

    //region Implementation of Questionnaire Responses and Preferences
    @Override
    public Questionnaire getQuestionnaire() {
        if(questionnaire==null)
            questionnaire=StaticUserModel.getQuestionnaire();
        return questionnaire;
    }

    @Override
    public ArrayList<Response> getUserResponses(String username) {
        return null;
    }

    @Override
    public Response getUserResponse(String username, Question question) {
        return null;
    }
    //endregion Implementation of Questionnaire Responses and Preferences

    @Override
    public User getUser(String username) {
        User ret = null;
        for (User u : getUsers()) {
            if(u.getName().toLowerCase().equals(username)){
                ret = u;
                break;
            }
        }
        return ret;
    }

    @Override
    public void saveCurrentUser() {

    }
}
