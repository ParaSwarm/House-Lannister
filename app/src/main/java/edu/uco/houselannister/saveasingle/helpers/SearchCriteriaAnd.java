package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 9/27/2016.
 */
public class SearchCriteriaAnd implements SearchCriteria {
    private SearchCriteria firstCriteria;
    private SearchCriteria secondCriteria;

    public SearchCriteriaAnd(SearchCriteria firstCriteria, SearchCriteria secondCriteria) {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }

    @Override
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users) {
        //checks the first set of users are in the criteria
        ArrayList<User> firstCriteriaUsers = firstCriteria.meetsSearchCriteria(users);
        //checks that the first set meets the second criteria
        return secondCriteria.meetsSearchCriteria(firstCriteriaUsers);
    }
}
