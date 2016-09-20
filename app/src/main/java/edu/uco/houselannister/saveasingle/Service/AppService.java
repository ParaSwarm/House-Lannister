package edu.uco.houselannister.saveasingle.Service;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.model.IServiceProxy;
import edu.uco.houselannister.saveasingle.model.Question;
import edu.uco.houselannister.saveasingle.model.Questionnaire;
import edu.uco.houselannister.saveasingle.model.Response;
import edu.uco.houselannister.saveasingle.model.User;

/**
 * Created by Gordon on 9/20/2016.
 */
public class AppService implements IServiceProxy {
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
