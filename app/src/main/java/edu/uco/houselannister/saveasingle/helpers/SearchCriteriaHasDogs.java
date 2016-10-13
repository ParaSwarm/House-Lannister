package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 10/6/2016.
 */
public class SearchCriteriaHasDogs implements SearchCriteria {
    private boolean lookingForDogs;
    public SearchCriteriaHasDogs(boolean lookingForDogs) {
        this.lookingForDogs = lookingForDogs;
    }
    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            if(lookingForDogs == user.getUserDemographics().isHasDogs()) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
    }
}
