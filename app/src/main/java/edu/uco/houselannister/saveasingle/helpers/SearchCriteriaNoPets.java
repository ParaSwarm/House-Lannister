package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 10/6/2016.
 */
public class SearchCriteriaNoPets implements SearchCriteria {
    private boolean lookingForNoPets;
    public SearchCriteriaNoPets(boolean lookingForNoPets) {
        this.lookingForNoPets = lookingForNoPets;
    }
    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            if(lookingForNoPets == user.getUserDemographics().isHasNoPets()) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
    }
}
