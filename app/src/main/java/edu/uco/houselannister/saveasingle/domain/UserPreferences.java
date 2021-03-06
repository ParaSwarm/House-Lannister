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

    private Language languagePreference;

    private boolean hasCats;

    private boolean hasDogs;

    private boolean hasNoPets;

    private boolean wantsKids;
    private boolean mightWantKids;
    private boolean doesNotWantKids;
    private boolean currentlyHasKids;
    private boolean doesNotCurrentlyHaveKids;

    private SearchDistances searchDistances;

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

    public Language getLanguagePreference() {return languagePreference;}

    public void setLanguagePreference(Language languagePreference) {this.languagePreference = languagePreference;}

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

    public SearchDistances getSearchDistances() {
        return searchDistances;
    }

    public void setSearchDistances(SearchDistances searchDistances) {
        this.searchDistances = searchDistances;
    }
}