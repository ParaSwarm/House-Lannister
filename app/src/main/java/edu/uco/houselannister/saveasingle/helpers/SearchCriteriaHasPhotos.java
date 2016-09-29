package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 9/28/2016.
 */
public class SearchCriteriaHasPhotos implements SearchCriteria {
    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            if(!user.getPhotos().isEmpty()) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
    }
}
