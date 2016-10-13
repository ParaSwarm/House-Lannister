package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Gender;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 9/28/2016.
 */
public class SearchCriteriaGenger implements SearchCriteria {

    private ArrayList<Gender> selectedGenders;

    public SearchCriteriaGenger(ArrayList<Gender> genders) {
        selectedGenders = genders;
    }

    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            for(Gender gender: selectedGenders) {
                if(user.getUserDemographics().getMyGender() == gender) {
                    matchingUsers.add(user);
                }
            }
        }
        return matchingUsers;
    }
}
