package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Status;
import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 10/5/2016.
 */
public class SearchCriteriaRelationhip implements SearchCriteria {
    ArrayList<Status> preferredStatus;

    public SearchCriteriaRelationhip(ArrayList<Status> usersPreferredStatus) {
        this.preferredStatus = usersPreferredStatus;
    }

    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> matchingUsers = new ArrayList<User>();
        for(User user: users) {
            for(Status status: preferredStatus) {
                if(user.getUserDemographics().getMyStatus() == status) {
                    matchingUsers.add(user);
                }
            }
        }
        return matchingUsers;
    }
}
