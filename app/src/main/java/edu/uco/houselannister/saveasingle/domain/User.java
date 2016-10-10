package edu.uco.houselannister.saveasingle.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.*;

public class User  implements Serializable, Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.password);
        dest.writeString(this.emailAddress);
        dest.writeSerializable(this.userNotificationPreferences);
        dest.writeSerializable(this.userDemographics);
        dest.writeSerializable(this.userPreferences);
        dest.writeSerializable(this.userExcludes);
        dest.writeList(this.photos);
        dest.writeSerializable(this.bio);
        dest.writeList(this.questionResponses);
        dest.writeSerializable(this.interactions);
        dest.writeSerializable(this.profilePhoto);
        dest.writeValue(this.isAdmin);
        dest.writeValue(this.enabled);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
        this.emailAddress = in.readString();
        this.userNotificationPreferences = (UserNotificationPreferences) in.readSerializable();
        this.userDemographics = (UserDemographics) in.readSerializable();
        this.userPreferences = (UserPreferences) in.readSerializable();
        this.userExcludes = (UserPreferences) in.readSerializable();
        this.photos = new ArrayList<Photo>();
        in.readList(this.photos, Photo.class.getClassLoader());
        this.bio = (Bio) in.readSerializable();
        this.questionResponses = new ArrayList<Response>();
        in.readList(this.questionResponses, Response.class.getClassLoader());
        this.interactions = (UserInteractions) in.readSerializable();
        this.profilePhoto = (Photo) in.readSerializable();
        this.isAdmin = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.enabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}