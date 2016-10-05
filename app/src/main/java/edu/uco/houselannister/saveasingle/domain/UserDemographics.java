package edu.uco.houselannister.saveasingle.domain;

import java.util.*;

public class UserDemographics {

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
}