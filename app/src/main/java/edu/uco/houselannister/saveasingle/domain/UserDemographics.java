package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class UserDemographics implements Serializable {

    private SalaryRange mySalaryRange;

    private Gender myGender;

    private EducationLevel myEducationLevel;

    private Ethnicity myEthnicity;

    private ZipCode myZipCode;

    private Integer myAge;

    private Religion myReligion;

    private ArrayList<Personality> personalityTags;

    private ArrayList<Relationship> relationshipTags;

    private ArrayList<Interests> interestTags;

    private Status myStatus;

    private Language myLanguage;

    private boolean hasCats;

    private boolean hasDogs;

    private boolean hasNoPets;
    private boolean wantsKids;
    private boolean mightWantKids;
    private boolean doesNotWantKids;
    private boolean currentlyHasKids;
    private boolean doesNotCurrentlyHaveKids;

    public SalaryRange getMySalaryRange() {
        return mySalaryRange;
    }

    public void setMySalaryRange(SalaryRange mySalaryRange) {
        this.mySalaryRange = mySalaryRange;
    }

    public Gender getMyGender() {
        return myGender;
    }

    public void setMyGender(Gender myGender) {
        this.myGender = myGender;
    }

    public EducationLevel getMyEducationLevel() {
        return myEducationLevel;
    }

    public void setMyEducationLevel(EducationLevel myEducationLevel) {
        this.myEducationLevel = myEducationLevel;
    }

    public Ethnicity getMyEthnicity() {
        return myEthnicity;
    }

    public void setMyEthnicity(Ethnicity myEthnicity) {
        this.myEthnicity = myEthnicity;
    }

    public ZipCode getMyZipCode() {
        return myZipCode;
    }

    public void setMyZipCode(ZipCode myZipCode) {
        this.myZipCode = myZipCode;
    }

    public Integer getMyAge() {
        return myAge;
    }

    public void setMyAge(Integer myAge) {
        this.myAge = myAge;
    }

    public Religion getMyReligion() {
        return myReligion;
    }

    public void setMyReligion(Religion myReligion) {
        this.myReligion = myReligion;
    }

    public ArrayList<Personality> getPersonalityTags() {
        return personalityTags;
    }

    public void setPersonalityTags(ArrayList<Personality> personalityTags) {
        this.personalityTags = personalityTags;
    }

    public ArrayList<Relationship> getRelationshipTags() {
        return relationshipTags;
    }

    public void setRelationshipTags(ArrayList<Relationship> relationshipTags) {
        this.relationshipTags = relationshipTags;
    }

    public ArrayList<Interests> getInterestTags() {
        return interestTags;
    }

    public void setInterestTags(ArrayList<Interests> interestTags) {
        this.interestTags = interestTags;
    }

    public Status getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(Status myStatus) {
        this.myStatus = myStatus;
    }

    public Language getMyLanguage() {return myLanguage;}

    public void setMyLanguage(Language myLanguage) {this.myLanguage = myLanguage;}

    public boolean isHasCats() {
        return hasCats;
    }

    public void setHasCats(boolean hasCats) {
        this.hasCats = hasCats;
    }

    public boolean isHasDogs() {
        return hasDogs;
    }

    public void setHasDogs(boolean hasDogs) {
        this.hasDogs = hasDogs;
    }

    public boolean isHasNoPets() {
        return hasNoPets;
    }

    public void setHasNoPets(boolean hasNoPets) {
        this.hasNoPets = hasNoPets;
    }

    public boolean isWantsKids() {
        return wantsKids;
    }

    public void setWantsKids(boolean wantsKids) {
        this.wantsKids = wantsKids;
    }

    public boolean isMightWantKids() {
        return mightWantKids;
    }

    public void setMightWantKids(boolean mightWantKids) {
        this.mightWantKids = mightWantKids;
    }

    public boolean isDoesNotWantKids() {
        return doesNotWantKids;
    }

    public void setDoesNotWantKids(boolean doesNotWantKids) {
        this.doesNotWantKids = doesNotWantKids;
    }

    public boolean isCurrentlyHasKids() {
        return currentlyHasKids;
    }

    public void setCurrentlyHasKids(boolean currentlyHasKids) {
        this.currentlyHasKids = currentlyHasKids;
    }

    public boolean isDoesNotCurrentlyHaveKids() {
        return doesNotCurrentlyHaveKids;
    }

    public void setDoesNotCurrentlyHaveKids(boolean doesNotCurrentlyHaveKids) {
        this.doesNotCurrentlyHaveKids = doesNotCurrentlyHaveKids;
    }
}