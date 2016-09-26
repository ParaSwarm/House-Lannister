package edu.uco.houselannister.saveasingle.service;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.*;

public class AppService implements ServiceProxy {

    private static ServiceProxy appServiceInstance = null;

    private ArrayList<User> users;

    private AppService() {
    }

    public static ServiceProxy getAppServiceInstance() {
        if (appServiceInstance == null){
            appServiceInstance = new AppService();
        }
        return appServiceInstance;
    }

    @Override
    public User GetUser(String username) {
        for (User u : StaticUserModel.getUsers())
            if (u.getName().toLowerCase().equals(username.toLowerCase())) {
                return u;
            }
        return null;
    }

    @Override
    public void SaveUser(User user) {
        int i = 0;
        for (; i < StaticUserModel.getUsers().size(); ++i) {
            if (StaticUserModel.getUsers().get(i).getName().toLowerCase().equals(user.getName().toLowerCase())) {
                break;
            }
        }
        StaticUserModel.getUsers().remove(i);
        StaticUserModel.getUsers().add(i, user);
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

    @Override
    public ArrayList<User> getUsers(){
        if(users == null)
            users = StaticUserModel.getUsers();
        return users;
    }
}
