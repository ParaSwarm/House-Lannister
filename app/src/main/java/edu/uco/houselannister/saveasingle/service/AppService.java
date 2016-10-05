package edu.uco.houselannister.saveasingle.service;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import edu.uco.houselannister.saveasingle.domain.*;


public class AppService implements ServiceProxy, Serializable {

    private User currentUser;
    private User impersonatedUser;
    private Questionnaire questionnaire;
    private ArrayList<User> users;

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

    public static ServiceProxy getAppServiceInstance(String filename) {
        StaticUserModel.setObjectStreams(filename);
        if (appServiceInstance == null) {
            appServiceInstance = StaticUserModel.ReadModel();
            if (appServiceInstance == null) {
                appServiceInstance = new AppService();

                try {
                    StaticUserModel.WriteModel(appServiceInstance);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return appServiceInstance;
    }


    //endregion Implementation of Singleton Pattern for Creation

    //region Implementation of Service Authentication Methods
    @Override
    public void Authenticate(String email, String password) {
        Boolean isAuthenticated;
        this.currentUser = null;
        this.impersonatedUser = null;
        for (User u : getUsers()) {
            isAuthenticated = email.toLowerCase().equals(u.getEmailAddress().toLowerCase()) && password.toLowerCase().equals(u.getPassword().toLowerCase());
            if (isAuthenticated) {
                this.currentUser = u;
                this.impersonatedUser = u;
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
        return this.currentUser;
    }

    @Override
    public User getCurrentUser() {
        return this.impersonatedUser;
    }

    @Override
    public void setCurrentUserImpersonation(User user) {
        this.impersonatedUser = user;
    }

    @Override
    public void resetCurrentUserImpersonation() {
        this.impersonatedUser = currentUser;
    }
    //endregion Implementation of Service Authentication Methods

    //region Implementation of Preferences
    @Override
    public Questionnaire getQuestionnaire() {
        if (questionnaire == null)
            questionnaire = StaticUserModel.getQuestionnaire();
        return questionnaire;
    }

    @Override
    public ArrayList<Response> getUserResponses(String username) {
        return getUser(username).getQuestionResponses();
    }

    @Override
    public Response getUserResponse(String username, Question question) {
        for (Response r : this.getUserResponses(username)) {
            if (r.getQuestion().equals(question.getQuestion())) return r;
        }
        return null;
    }
    //endregion Implementation of Questionnaire Responses and Preferences

    //region Implementation of UserProfile Interface
    @Override
    public User getUser(String username) {
        for (User u : getUsers()) {
            if (u.getName().toLowerCase().equals(username.toLowerCase())) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        int i = 0;
        users = getUsers();
        for (; i < users.size(); ++i) {
            if (getUsers().get(i).getName().toLowerCase().equals(user.getName().toLowerCase())) {
                break;
            }
        }

        if (users.size() > i) {
            users.remove(i);
            users.add(i, user);
        } else {
            users.add(user);
        }

        try {
            StaticUserModel.WriteModel(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        int i = 0;
        for (; i < getUsers().size(); ++i) {
            if (getUsers().get(i).getName().toLowerCase().equals(user.getName().toLowerCase())) {
                break;
            }
        }

        if (getUsers().size() > i) {
            getUsers().remove(i);
        }

        try {
            StaticUserModel.WriteModel(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion Implementation of UserProfile Interface

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


    //endregion Implementation of Service Model Interface


}
