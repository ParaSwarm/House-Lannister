package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;

/**
 * Created by Gordon on 9/20/2016.
 */
public interface IQuestionnaire {

    Questionnaire GetQuestionnaire(String username);

    ArrayList<Response> GetUserResponses(String username);

    Response GetUserResponse (String username, Question question);
}
