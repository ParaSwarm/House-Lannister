package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;

/**
 * Created by Gordon on 9/20/2016.
 * Preferences are the tags and questionnaires for users
 * These are accessible through the user's preference members
 */
public interface IPreferences {

    Questionnaire GetQuestionnaire(String username);

    ArrayList<Response> GetUserResponses(String username);

    Response GetUserResponse (String username, Question question);
}
