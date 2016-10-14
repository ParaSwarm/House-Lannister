package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;
import java.util.logging.Level;

import edu.uco.houselannister.saveasingle.domain.EducationLevel;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 10/5/2016.
 */
public class SearchCriteriaEducationLevel implements SearchCriteria {
    private ArrayList<EducationLevel> chosenLevel;

    public SearchCriteriaEducationLevel(ArrayList<EducationLevel> level) {
        this.chosenLevel = level;
    }

    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            EducationLevel usersLevel = user.getUserDemographics().getMyEducationLevel();
            for(EducationLevel level: chosenLevel) {
                if(usersLevel.ordinal() >= level.ordinal()) {
                    if(!matchingUsers.contains(user)) {
                        matchingUsers.add(user);
                    }
                    else{
                        //do not add a duplicate
                    }
                }
            }
        }
        return matchingUsers;
    }
}
