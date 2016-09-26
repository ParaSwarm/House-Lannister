package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Authentication;
import edu.uco.houselannister.saveasingle.domain.Preferences;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by Gordon on 9/25/2016.
 */
public class PreferenceModel implements Preferences {

    private Questionnaire questionnaire;

    private ServiceProxy proxy;

    private static Preferences preferencesInstance = null;

    private PreferenceModel(ServiceProxy proxy) {
        this.proxy = proxy;
    }

    public static Preferences getPreferencesInstance(ServiceProxy proxy) {
        if (preferencesInstance == null)
            preferencesInstance = new PreferenceModel(proxy);
        return preferencesInstance;
    }

    @Override
    public Questionnaire getQuestionnaire() {
        if (questionnaire == null)
            questionnaire = proxy.GetQuestionnaire();
        return questionnaire;
    }

    @Override
    public ArrayList<Response> getUserResponses(String username) {
        return null;
    }

    @Override
    public Response getUserResponse(String username, Question question) {
        return null;
    }
}
