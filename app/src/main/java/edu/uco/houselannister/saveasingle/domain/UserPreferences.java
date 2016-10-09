package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class UserPreferences implements Serializable {

    private ArrayList<SalaryRange> salaryRanges;

    private ArrayList<Gender> genders;

    private ArrayList<EducationLevel> eduLevels;

    private ArrayList<Ethnicity> ethnicities;

    private Integer zipCodeRadius;

    private Integer ageLow;

    private Integer ageHigh;
    //TODO: Do many people worship in multiple religions?
    private ArrayList<Religion> religions;

    private ArrayList<Personality> personalityTags;

    private ArrayList<Relationship> relationshipTags;

    private ArrayList<Interests> interestTags;

    private ArrayList<Status> status;

    private boolean preferencesSet;

    private boolean prefersPhotos;

    private boolean openToPoly;

    private String languagePreference;

    public ArrayList<SalaryRange> getSalaryRanges() {
        return salaryRanges;
    }

    public void setSalaryRanges(ArrayList<SalaryRange> salaryRanges) {
        this.salaryRanges = salaryRanges;
    }

    public ArrayList<Gender> getGenders() {
        return genders;
    }

    public void setGenders(ArrayList<Gender> genders) {
        this.genders = genders;
    }

    public ArrayList<EducationLevel> getEduLevels() {
        return eduLevels;
    }

    public void setEduLevels(ArrayList<EducationLevel> eduLevels) {
        this.eduLevels = eduLevels;
    }

    public ArrayList<Ethnicity> getEthnicities() {
        return ethnicities;
    }

    public void setEthnicities(ArrayList<Ethnicity> ethnicities) {
        this.ethnicities = ethnicities;
    }

    public Integer getZipCodeRadius() {
        return zipCodeRadius;
    }

    public void setZipCodeRadius(Integer zipCodeRadius) {
        this.zipCodeRadius = zipCodeRadius;
    }

    public Integer getAgeLow() {
        return ageLow;
    }

    public void setAgeLow(Integer ageLow) {
        this.ageLow = ageLow;
    }

    public Integer getAgeHigh() {
        return ageHigh;
    }

    public void setAgeHigh(Integer ageHigh) {
        this.ageHigh = ageHigh;
    }

    public ArrayList<Religion> getReligions() {
        return religions;
    }

    public void setReligions(ArrayList<Religion> religions) {
        this.religions = religions;
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

    public ArrayList<Status> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<Status> status) {
        this.status = status;
    }

    public boolean preferencesSet() {return preferencesSet;}

    public void preferencesApplied(boolean set) {this.preferencesSet = set;}

    public boolean getPrefersPhotos() {return prefersPhotos;}

    public void setPrefersPhotos(boolean preference) {this.prefersPhotos = preference;}

    public boolean getOpenToPoly(){return openToPoly;}

    public void setOpenToPoly(boolean openToPoly) {this.openToPoly = openToPoly;}

    public String getLanguagePreference() {return languagePreference;}

    public void setLanguagePreference(String languagePreference) {this.languagePreference = languagePreference;}
}