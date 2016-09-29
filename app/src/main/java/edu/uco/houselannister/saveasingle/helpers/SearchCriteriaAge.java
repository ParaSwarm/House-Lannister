package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 9/28/2016.
 */
public class SearchCriteriaAge implements SearchCriteria {
    private int minAge, maxAge;

    public SearchCriteriaAge(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();

        for(User user: users) {
            if(user.getUserDemographics().getMyAge()>= minAge && user.getUserDemographics().getMyAge() <= maxAge) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
    }
}
