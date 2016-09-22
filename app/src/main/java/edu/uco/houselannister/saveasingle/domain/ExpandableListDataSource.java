package edu.uco.houselannister.saveasingle.domain;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.uco.houselannister.saveasingle.R;

/**
 * Created by ryan on 9/21/2016.
 */
public class ExpandableListDataSource {


    public static Map<String, List<String>> getData(Context context) {
        Map<String, List<String>> expandableListData = new TreeMap<>();

        //this is the main set of items in the drawer
        List<String> menu = Arrays.asList(context.getResources().getStringArray(R.array.navigation_titles));

        //for each sub array of menu options
        List<String> home = Arrays.asList(context.getResources().getStringArray(R.array.home_menu_titles));
        List<String> settings = Arrays.asList(context.getResources().getStringArray(R.array.user_profile_titles));

        //the second item in the array is for settings so I am adding the sub list onto that
        expandableListData.put(menu.get(0), home); //add the home array of strings to the home expandable list
        expandableListData.put(menu.get(1), settings); //add the settings options for the settings expandable

        return expandableListData;
    }
}
