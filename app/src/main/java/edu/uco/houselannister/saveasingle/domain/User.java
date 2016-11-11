package edu.uco.houselannister.saveasingle.domain;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class User  implements Serializable, Parcelable {

    private String name;

    private String password;

    private String emailAddress;

    private UserNotificationPreferences userNotificationPreferences;

    private UserDemographics userDemographics;

    private UserPreferences userPreferences;

    private UserPreferences userExcludes;

    private UserStatus status;

    private ArrayList<Photo> photos;

    private Bio bio;

    private ArrayList<Response> questionResponses;

    private UserInteractions interactions;

    private Photo profilePhoto;

    private Boolean isAdmin;

    private Boolean enabled;
    private Location location;

    private String fullname;
    private int age;
    private String position;
    private String education;
    private double height;
    private String religion;
    private String ethnicity;
    private int smoking;
    private int bodyType;
    private String work;
    private double income;
    private int marriedStatus;
    private int children;
    private String story;
    private String perfectMatch;
    private int gender;
    private boolean galleryPrivate;
    private boolean profilePrivate;

    public static final String[] GenderValues = new String[] {"Male", "Female"};
    public static final String[] SmokingValues = new String[] {"Never", "Socially Only", "Sometimes", "about 10 per day", "more than 20 per day"};
    public static final String[] BodyTypeValues = new String[] {"Slim", "Average", "Fat"};
    public static final String[] MarriedStatusValues = new String[] {"Not Married", "Married", "Divorced"};

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

    public UserStatus getStatus() {
        if(status == null){
            status = new UserStatus();
        }

        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public User() {
        fullname = "";
        position = "";
        education = "";
        religion = "";
        ethnicity = "";
        work = "";
        story = "";
        perfectMatch = "";
        photos = new ArrayList<Photo>();
        profilePhoto = new Photo();
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
        fullname = "";
        position = "";
        education = "";
        religion = "";
        ethnicity = "";
        work = "";
        story = "";
        perfectMatch = "";
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public int getSmoking() {
        return smoking;
    }

    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }

    public int getBodyType() {
        return bodyType;
    }

    public void setBodyType(int bodyType) {
        this.bodyType = bodyType;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getMarriedStatus() {
        return marriedStatus;
    }

    public void setMarriedStatus(int marriedStatus) {
        this.marriedStatus = marriedStatus;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getPerfectMatch() {
        return perfectMatch;
    }

    public void setPerfectMatch(String perfectMatch) {
        this.perfectMatch = perfectMatch;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean getGalleryPrivate() {
        return galleryPrivate;
    }

    public void setGalleryPrivate(boolean galleryPrivate) {
        this.galleryPrivate = galleryPrivate;
    }

    public boolean getProfilePrivate() {
        return profilePrivate;
    }

    public void setProfilePrivate(boolean profilePrivate) {
        this.profilePrivate = profilePrivate;
    }

}