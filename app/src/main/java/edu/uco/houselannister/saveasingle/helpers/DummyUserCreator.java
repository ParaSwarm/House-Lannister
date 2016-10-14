package edu.uco.houselannister.saveasingle.helpers;

import java.util.ArrayList;
import java.util.Random;

import edu.uco.houselannister.saveasingle.domain.Bio;
import edu.uco.houselannister.saveasingle.domain.EducationLevel;
import edu.uco.houselannister.saveasingle.domain.Ethnicity;
import edu.uco.houselannister.saveasingle.domain.Gender;
import edu.uco.houselannister.saveasingle.domain.Interests;
import edu.uco.houselannister.saveasingle.domain.Language;
import edu.uco.houselannister.saveasingle.domain.Personality;
import edu.uco.houselannister.saveasingle.domain.Relationship;
import edu.uco.houselannister.saveasingle.domain.Religion;
import edu.uco.houselannister.saveasingle.domain.SalaryRange;
import edu.uco.houselannister.saveasingle.domain.SearchDistances;
import edu.uco.houselannister.saveasingle.domain.Status;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.domain.UserDemographics;
import edu.uco.houselannister.saveasingle.domain.UserPreferences;

/**
 * Created by ryan on 10/9/2016.
 */
public class DummyUserCreator {
    private ArrayList<User> createdUsers;
    private Random r = new Random();

    public DummyUserCreator() {

    }

    public ArrayList<User> createUsers(int numberToCreate){
        for(int i = 0; i <= numberToCreate;i++){
            User user = new User();
            user.setEmailAddress(randomString() + "@test.com");
            user.setName(randomString());
            user.setAdmin(randomBool());
            user.setEnabled(randomBool());
            user.setBio(randomBio());
            user.setPassword(randomString());
            user.setUserDemographics(randomUserDemographics());
            user.setUserPreferences(randomUserPreferences());
        }
        return createdUsers;
    }

    public User createUser() {
        User createdUser = new User();
        return createdUser;
    }

    private String randomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        final int N = 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    private boolean randomBool() {
        return r.nextBoolean();
    }

    private Bio randomBio() {
        Bio bio = new Bio();
        bio.setAboutMe(randomString());
        bio.setAboutYou(randomString());
        bio.setWhyMessageMe(randomString());
        return bio;
    }

    private UserDemographics randomUserDemographics() {
        UserDemographics userDemographics = new UserDemographics();
        userDemographics.setCurrentlyHasKids(randomBool());
        userDemographics.setDoesNotCurrentlyHaveKids(randomBool());
        userDemographics.setDoesNotWantKids(randomBool());
        userDemographics.setHasCats(randomBool());
        userDemographics.setHasDogs(randomBool());
        userDemographics.setHasNoPets(randomBool());
        userDemographics.setMightWantKids(randomBool());
        userDemographics.setMyAge(r.nextInt());
        userDemographics.setMyGender(Gender.getRandom());
        userDemographics.setMySalaryRange(SalaryRange.getRandom());
        userDemographics.setMyEducationLevel(EducationLevel.getRandom());
        userDemographics.setMyEthnicity(Ethnicity.getRandom());
        userDemographics.setMyReligion(Religion.getRandom());
        userDemographics.setPersonalityTags(Personality.getRandomPersonalities(2));
        userDemographics.setRelationshipTags(Relationship.getRandomRelationships(1));
        userDemographics.setInterestTags(Interests.getRandomInterests(3));
        userDemographics.setMyStatus(Status.getRandom());
        userDemographics.setMyLanguage(Language.getRandom());
        return userDemographics;
    }

    private UserPreferences randomUserPreferences(){
        UserPreferences preferences = new UserPreferences();
        preferences.setCurrentlyHasKids(randomBool());
        preferences.setDoesNotCurrentlyHaveKids(randomBool());
        preferences.setDoesNotWantKids(randomBool());
        preferences.setHasCats(randomBool());
        preferences.setHasDogs(randomBool());
        preferences.setHasNoPets(randomBool());
        preferences.setMightWantKids(randomBool());
        preferences.setAgeLow(r.nextInt());
        preferences.setAgeHigh(r.nextInt());
        preferences.setGenders(Gender.getRandoms(2));
        preferences.setSalaryRanges(SalaryRange.getRandoms(2));
        preferences.setEduLevels(EducationLevel.getRandoms(3));
        preferences.setEthnicities(Ethnicity.getRandoms(2));
        preferences.setReligions(Religion.getRandoms(2));
        preferences.setPersonalityTags(Personality.getRandomPersonalities(2));
        preferences.setRelationshipTags(Relationship.getRandomRelationships(1));
        preferences.setInterestTags(Interests.getRandomInterests(3));
        preferences.setStatus(Status.getRandoms(2));
        preferences.setLanguagePreference(Language.getRandom());
        preferences.setSearchDistances(SearchDistances.getRandom());
        return preferences;
    }
}
