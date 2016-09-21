package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;
import java.util.Objects;

import edu.uco.houselannister.saveasingle.domain.Authentication;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Preferences;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.domain.UserProfile;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.domain.User;

public class AppModel implements Model {

    private ServiceProxy proxy;

    private User currentUser;

    private Boolean isAuthenticated;

    private Questionnaire questionnaire;

    //region Implementation of Singleton Pattern for Model
    private AppModel(ServiceProxy proxy) {
        this.proxy = proxy;
    }

    public static AppModel createAppModel(ServiceProxy proxy) {
        return new AppModel(proxy);
    }
    //endregion Implementation of Singleton Pattern for Model

    //region Implementation if Authentication
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
    //endregion Implementation if Authentication

    //region Implementation of Preferences
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
    //endregion Implementation of Preferences

    //region Implementation of User Profile Interface.
    @Override
    public User GetUser(String username) {
        return proxy.GetUser(username);
    }

    @Override
    public void saveCurrentUser() {
        this.proxy.SaveUser(currentUser);
    }
    //endregion Implementation of User Profile Interface.

    //region Implementation of Model
    @Override
    public User getCurrentUser() {
        return currentUser;
    }
    //endregion
}
