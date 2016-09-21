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

    /**
     * Returns fake data of films
     *
     * @param context
     * @return
     */
    public static Map<String, List<String>> getData(Context context) {
        Map<String, List<String>> expandableListData = new TreeMap<>();

        List<String> menu = Arrays.asList(context.getResources().getStringArray(R.array.navigation_titles));
        //copy above for each sub array of menu options
        List<String> userProfile = Arrays.asList(context.getResources().getStringArray(R.array.user_profile_titles));

        expandableListData.put(menu.get(0), userProfile);

        return expandableListData;
    }
}
