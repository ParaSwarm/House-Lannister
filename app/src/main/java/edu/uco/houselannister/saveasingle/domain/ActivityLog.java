package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class ActivityLog implements Serializable {

    private ArrayList<UserActivity> activities;

    public ArrayList<UserActivity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<UserActivity> activities) {
        this.activities = activities;
    }



}