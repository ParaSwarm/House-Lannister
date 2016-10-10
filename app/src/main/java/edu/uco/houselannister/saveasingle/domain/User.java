package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    private String name;

    private String password;

    private String emailAddress;

    private UserNotificationPreferences userNotificationPreferences;

    private UserDemographics userDemographics;

    private UserPreferences userPreferences;

    private UserPreferences userExcludes;

    private ArrayList<Photo> photos;

    private Bio bio;

    private ArrayList<Response> questionResponses;

    private UserInteractions interactions;

    private Photo profilePhoto;

    private Boolean isAdmin;

    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserNotificationPreferences getUserNotificationPreferences() {
        if (this.userNotificationPreferences == null)
            this.userNotificationPreferences = new UserNotificationPreferences();
        return userNotificationPreferences;
    }

    public void setUserNotificationPreferences(UserNotificationPreferences userNotificationPreferences) {
        this.userNotificationPreferences = userNotificationPreferences;
    }

    public UserDemographics getUserDemographics() {
        if (userDemographics == null)
            userDemographics = new UserDemographics();
        return userDemographics;
    }

    public void setUserDemographics(UserDemographics userDemographics) {
        this.userDemographics = userDemographics;
    }

    public UserPreferences getUserPreferences() {
        if (this.userPreferences == null)
            this.userPreferences = new UserPreferences();
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    public UserPreferences getUserExcludes() {
        if (this.userExcludes == null)
            this.userExcludes = new UserPreferences();
        return userExcludes;
    }

    public void setUserExcludes(UserPreferences userExcludes) {
        this.userExcludes = userExcludes;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public Bio getBio() {
        if (this.bio == null)
            this.bio = new Bio() {
                {
                    setAboutMe("");
                    setAboutYou("");
                    setWhyMessageMe("");
                }
            };
        return bio;
    }

    public void setBio(Bio bio) {
        this.bio = bio;
    }

    public ArrayList<Response> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(ArrayList<Response> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public UserInteractions getInteractions() {
        if (interactions == null)
            interactions = new UserInteractions();
        return interactions;
    }

    public void setInteractions(UserInteractions interactions) {
        this.interactions = interactions;
    }

    public Photo getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Photo profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        if (this.isAdmin == null)
            this.isAdmin = false;
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getEnabled() {
        if (this.enabled == null)
            this.enabled = true;
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}