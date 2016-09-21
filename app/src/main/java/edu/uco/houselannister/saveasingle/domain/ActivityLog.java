package edu.uco.houselannister.saveasingle.domain;

import java.util.*;

/**
 * 
 */
public class ActivityLog {

    /**
     * Default constructor
     */
    public ActivityLog() {
    }

    public ArrayList<UserActivity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<UserActivity> activities) {
        this.activities = activities;
    }

    /**
     * 
     */
    private ArrayList<UserActivity> activities;


}