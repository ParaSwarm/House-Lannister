package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Religion;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 10/5/2016.
 */
public class SearchCriteriaReligion implements SearchCriteria {
    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            for(Religion religion: Religion.values()) {
                if(user.getUserDemographics().getMyReligion() == religion) {
                    matchingUsers.add(user);
                }
            }
        }
        return matchingUsers;
    }
}
