package edu.uco.houselannister.saveasingle.model;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Preferences;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;

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
            questionnaire = proxy.getQuestionnaire();
        return questionnaire;
    }

    @Override
    public ArrayList<Response> getUserResponses(String username) {
        return proxy.getUserResponses(username);
    }

    @Override
    public Response getUserResponse(String username, Question question) {
        return proxy.getUserResponse(username, question);
    }
}
