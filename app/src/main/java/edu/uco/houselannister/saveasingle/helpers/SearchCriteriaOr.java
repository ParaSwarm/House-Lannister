package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 9/27/2016.
 */
public class SearchCriteriaOr implements SearchCriteria {
    private SearchCriteria firstCriteria;
    private SearchCriteria secondCritera;

    public SearchCriteriaOr(SearchCriteria firstCriteria, SearchCriteria secondCritera) {
        this.firstCriteria = firstCriteria;
        this.secondCritera = secondCritera;
    }

    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        ArrayList<User> firstCriteriaUsers = firstCriteria.meetsSearchCriteria(users);
        ArrayList<User> secondCriteriaUsers = secondCritera.meetsSearchCriteria(users);

        for(User user: secondCriteriaUsers) {
            if(!firstCriteriaUsers.contains(user)) {
                firstCriteriaUsers.add(user);
            }
        }
        return firstCriteriaUsers;
    }
}
