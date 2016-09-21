package edu.uco.houselannister.saveasingle.domain;

import java.util.ArrayList;

/**
 * Created by Gordon on 9/20/2016.
 * Preferences are the tags and questionnaires for users
 * These are accessible through the user's preference members
 */
public interface Preferences {

    Questionnaire getQuestionnaire(String username);

    ArrayList<Response> getUserResponses(String username);

    Response getUserResponse(String username, Question question);
}
