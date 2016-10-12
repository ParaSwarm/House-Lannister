package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 10/6/2016.
 */
public class SearchCriteriaHasCats implements SearchCriteria {
    private boolean lookingForCats;
    public SearchCriteriaHasCats(boolean lookingForCats) {
        this.lookingForCats = lookingForCats;
    }
    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            if(lookingForCats == user.getUserDemographics().isHasCats()) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
    }
}
