package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Gender;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by ryan on 9/28/2016.
 */
public class SearchCriteriaGenger implements SearchCriteria {

    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            for(Gender gender: Gender.values()) {
                if(user.getUserDemographics().getMyGender() == gender) {
                    matchingUsers.add(user);
                }
            }
        }
        return matchingUsers;
    }
}
