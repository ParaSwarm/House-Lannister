package edu.uco.houselannister.saveasingle.domain;

import java.util.ArrayList;

/**
 * Created by Gordon on 9/20/2016.
 * This interface provides methods to access the Service
 * for the model.  The model uses the Service proxy to
 * access the RealSubject in the Proxy pattern.
 */
public interface ServiceProxy {

    User GetUser(String username);
    
    void SaveUser(User user);
    
    Questionnaire GetQuestionnaire();

    ArrayList<Response> GetUserResponses(String username);

    Response GetUserResponse(String username, Question question);

    ArrayList<User> getUsers();
}
