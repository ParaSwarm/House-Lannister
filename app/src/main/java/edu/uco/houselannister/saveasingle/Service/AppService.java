package edu.uco.houselannister.saveasingle.service;

import java.util.ArrayList;
import edu.uco.houselannister.saveasingle.domain.*;

public class AppService implements ServiceProxy {

    private AppService() {
    }

    public static AppService createAppService() {
        return new AppService();
    }

    @Override
    public User GetUser(String username) {
        for (User u : StaticUserModel.Users())
            if (u.getName().toLowerCase().equals(username.toLowerCase())) {
                return u;
            }
        return null;
    }

    @Override
    public void SaveUser(User user) {
        int i=0;
        for(; i < StaticUserModel.Users().size(); ++i){
            if (StaticUserModel.Users().get(i).getName().toLowerCase().equals(user.getName().toLowerCase())) {
                break;
            }
        }
        StaticUserModel.Users().remove(i);
        StaticUserModel.Users().add(i,user);
    }

    @Override
    public Questionnaire GetQuestionnaire() {
        return null;
    }

    @Override
    public ArrayList<Response> GetUserResponses(String username) {
        return null;
    }

    @Override
    public Response GetUserResponse(String username, Question question) {
        return null;
    }
}
