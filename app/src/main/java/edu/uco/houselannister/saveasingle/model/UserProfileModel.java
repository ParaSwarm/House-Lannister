package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Preferences;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.domain.UserProfile;

public class UserProfileModel implements UserProfile {


    private ServiceProxy proxy;

    private static UserProfile userProfileInstance = null;

    private UserProfileModel(ServiceProxy proxy) {
        this.proxy = proxy;
    }

    public static UserProfile getUserProfileInstance(ServiceProxy proxy) {
        if (userProfileInstance == null)
            userProfileInstance = new UserProfileModel(proxy);
        return userProfileInstance;
    }

    @Override
    public User getUser(String username) {
        return proxy.getUser(username);
    }

    @Override
    public void saveUser(User user) {
        this.proxy.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        this.proxy.deleteUser(user);
    }
}
