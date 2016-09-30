package edu.uco.houselannister.saveasingle.helpers;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

/**
 * Created by ryan on 9/27/2016.
 */
public interface SearchCriteria {
    public ArrayList<User> meetsSearchCriteria(ArrayList<User> users);
}
