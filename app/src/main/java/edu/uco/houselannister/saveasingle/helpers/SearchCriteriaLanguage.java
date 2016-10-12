package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Language;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 10/5/2016.
 */
public class SearchCriteriaLanguage implements SearchCriteria {
    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            for(Language language: Language.values()) {
                if(user.getUserDemographics().getMyLanguage() == language) {
                    matchingUsers.add(user);
                }
            }
        }
        return matchingUsers;
    }
}
