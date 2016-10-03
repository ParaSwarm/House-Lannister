package edu.uco.houselannister.saveasingle.service;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import edu.uco.houselannister.saveasingle.domain.*;

public class StaticUserModel {

    //region Private Fields
    private static Questionnaire questionnaire = null;
    private static ArrayList<User> users = null;
    private static ArrayList<ZipCode> zipCodes = null;

    private static String mFilename;


    //endregion Private Fields

    //region Top Level Objects (Users and Questionnaires)
    public static Questionnaire getQuestionnaire() {
        if (questionnaire == null) {
            questionnaire = new Questionnaire();

            ArrayList<Question> questions = new ArrayList<>();
            questions.add(CreateQuestion(false, QuestionCategory.HYGIENE, "How frequently do you floss?", QuestionResponseType.FREQUENCY));
            questions.add(CreateQuestion(false, QuestionCategory.POLITICS, "Are going to vote in the next presidential election?", QuestionResponseType.LIKELIHOOD));
            questions.add(CreateQuestion(false, QuestionCategory.RELIGION, "How often do you go to church?", QuestionResponseType.FREQUENCY));
            questions.add(CreateQuestion(false, QuestionCategory.HYGIENE, "How important is shaving?", QuestionResponseType.IMPORTANCE));
            questions.add(CreateQuestion(false, QuestionCategory.FOOD, "Do you like to try new dishes?", QuestionResponseType.YES_NO));

            questionnaire.setQuestions(questions);
        }
        return questionnaire;
    }

    public static ArrayList<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
            users.add(CreateUser("Jackson", false, "password", "jackson@uco.edu", true));
            users.add(CreateUser("Sierra", false, "password", "sierra@uco.edu", false));
            users.add(CreateUser("Goliath", true, "password", "goliath@gmail.com", true));

            // Interactions must be set after all users are created
            CreateUserInteractions();
        }


        return users;
    }
    //endregion  Top Level Objects (Users and Questionnaires)

    //region Generate Sample Questionnaire
    private static Question CreateQuestion(Boolean allowMultipleResponses, QuestionCategory questionCategory, String questionText, QuestionResponseType responseType) {
        Question question = new Question();
        question.setAllowMultipleResponses(allowMultipleResponses);
        question.setCategory(questionCategory);
        question.setQuestion(questionText);
        question.setResponses(getQuestionResponsesByType(responseType));
        return question;
    }

    private enum QuestionResponseType {YES_NO, YES_NO_MAYBE, FREQUENCY, IMPORTANCE, AGREEMENT, LIKELIHOOD}

    private static ArrayList<String> getQuestionResponsesByType(QuestionResponseType type) {
        ArrayList<String> ret = new ArrayList<>();
        switch (type) {
            case YES_NO:
                ret.add("Yes");
                ret.add("No");
                ret.add("Prefer not to say");
                break;
            case YES_NO_MAYBE:
                ret.add("Yes");
                ret.add("No");
                ret.add("Maybe");
                break;
            case FREQUENCY:
                ret.add("Always");
                ret.add("Sometimes");
                ret.add("Never");
                break;
            case IMPORTANCE:
                ret.add("Very Important");
                ret.add("Somewhat Important");
                ret.add("Not Important");
                break;
            case AGREEMENT:
                ret.add("Strongly Agree");
                ret.add("Somewhat Agree");
                ret.add("Neither Agree nor Disagree");
                ret.add("Somewhat Disagree");
                ret.add("Strongly Disagree");
                break;
            case LIKELIHOOD:
                ret.add("It is certain");
                ret.add("Not very likely");
                ret.add("Undecided");
                ret.add("Probably not");
                ret.add("Absolutely not");
                break;
            default:
                break;
        }
        return ret;
    }
    //endregion Generate Sample Questionnaire

    //region Create User Main Method
    private static User CreateUser(String username, Boolean isAdmin, String userPassword, String userEmail, Boolean enabled) {

        User ret = new User();

        ret.setName(username);
        ret.setAdmin(isAdmin);
        ret.setEmailAddress(userEmail);
        ret.setPassword(userPassword);
        ret.setEnabled(enabled);

        ret.setBio(CreateBio());

        ArrayList<Response> responses = new ArrayList<>();
        responses.add(CreateResponse(0, "I like my teeth", 0, 0, 1));
        responses.add(CreateResponse(1, "People should vote", 0, 0, 1, 2));
        ret.setQuestionResponses(responses);

        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(CreatePhoto(false));
        photos.add(CreatePhoto(true));
        ret.setPhotos(photos);
        ret.setProfilePhoto(photos.get(0));

        ret.setUserDemographics(CreateUserDemographics());
        ret.setUserPreferences(CreateUserPreferences());
        ret.setUserExcludes(CreateUserExcludes());

        ret.setUserNotificationPreferences(CreateUserNotificationPreferences());

        return ret;
    }
    //endregion Create User Main Method

    //region Create User Helpers
    private static Bio CreateBio() {
        Bio bio = new Bio();
        bio.setAboutMe("This is stuff about me.");
        bio.setAboutYou("This is stuff about you.");
        bio.setWhyMessageMe("This is why you should message me.");
        return bio;
    }

    private static Response CreateResponse(Integer questionIndex, String explanation, Integer myResponse, Integer... acceptableResponseParams) {
        Response response = new Response();

        response.setQuestion(getQuestionnaire().getQuestions().get(questionIndex));

        response.setExplanation(explanation);

        ArrayList<Integer> myResponses = new ArrayList<>();
        myResponses.add(myResponse);
        response.setResponses(myResponses);

        ArrayList<Integer> acceptableResponses = new ArrayList<>();
        Collections.addAll(acceptableResponses, acceptableResponseParams);
        response.setAcceptableResponses(acceptableResponses);

        return response;
    }

    private static Photo CreatePhoto(Boolean isPrivate) {
        Photo photo = new Photo();
        photo.setPrivate(isPrivate);
        return photo;
    }

    private static UserDemographics CreateUserDemographics() {
        UserDemographics userDemographics = new UserDemographics();
        userDemographics.setMyAge(21);
        userDemographics.setMyEducationLevel(EducationLevel.SOMECOLLEGE);
        userDemographics.setMyEthnicity(Ethnicity.NONSPECIFIC);
        userDemographics.setMyGender(Gender.NONSPECIFIC);
        userDemographics.setMyReligion(Religion.NONRELIGIOUS);
        userDemographics.setMySalaryRange(SalaryRange.GETTINGBY);
        userDemographics.setMyStatus(Status.COMPLICATED);
        userDemographics.setMyZipCode(CreateZipCodes().get(3));
        userDemographics.setInterestTags(new ArrayList<Interests>() {{
            add(Interests.COOKING);
            add(Interests.CYCLING);
        }});
        userDemographics.setPersonalityTags(new ArrayList<Personality>() {{
            add(Personality.EXTROVERT);
            add(Personality.INSIGHTFUL);
        }});
        userDemographics.setRelationshipTags(new ArrayList<Relationship>() {{
            add(Relationship.DATING);
            add(Relationship.PLATONIC);
        }});


        return userDemographics;
    }

    private static UserPreferences CreateUserPreferences() {
        UserPreferences userPreferences = new UserPreferences();

        userPreferences.setAgeLow(25);
        userPreferences.setAgeHigh(65);
        userPreferences.setZipCodeRadius(10);

        userPreferences.setPersonalityTags(new ArrayList<Personality>() {{
            add(Personality.JUDGMENTAL);
        }});
        userPreferences.setRelationshipTags(new ArrayList<Relationship>() {{
            add(Relationship.SHORTTERM);
            add(Relationship.INTIMATE);
        }});
        userPreferences.setInterestTags(new ArrayList<Interests>() {{
            add(Interests.COLLECTING);
            add(Interests.GARDENING);
        }});
        userPreferences.setEduLevels(new ArrayList<EducationLevel>() {{
            add(EducationLevel.BACHELORS);
            add(EducationLevel.MASTERS);
        }});
        userPreferences.setEthnicities(new ArrayList<Ethnicity>() {{
            add(Ethnicity.HISPANIC);
            add(Ethnicity.OTHER);
        }});
        userPreferences.setGenders(new ArrayList<Gender>() {{
            add(Gender.NONSPECIFIC);
            add(Gender.FEMALE);
        }});
        userPreferences.setReligions(new ArrayList<Religion>() {{
            add(Religion.CONFUCIUS);
            add(Religion.JAINIST);
        }});
        userPreferences.setSalaryRanges(new ArrayList<SalaryRange>() {{
            add(SalaryRange.DADDYWARBUCKS);
            add(SalaryRange.POORSLOB);
        }});
        userPreferences.setStatus(new ArrayList<Status>() {{
            add(Status.SINGLE);
        }});
        return userPreferences;
    }

    private static UserPreferences CreateUserExcludes() {
        UserPreferences userExcludes = new UserPreferences();

        userExcludes.setAgeLow(18);
        userExcludes.setAgeHigh(24);
        userExcludes.setZipCodeRadius(100);
        userExcludes.setPersonalityTags(new ArrayList<Personality>() {{
            add(Personality.THOUGHTFUL);
        }});
        userExcludes.setRelationshipTags(new ArrayList<Relationship>() {{
            add(Relationship.PLATONIC);
            add(Relationship.SERIOUS);
        }});
        userExcludes.setInterestTags(new ArrayList<Interests>() {{
            add(Interests.MOVIES);
            add(Interests.MARTIALARTS);
        }});
        userExcludes.setEduLevels(new ArrayList<EducationLevel>() {{
            add(EducationLevel.NONE);
            add(EducationLevel.DOCTORAL);
        }});
        userExcludes.setEthnicities(new ArrayList<Ethnicity>() {{
            add(Ethnicity.OTHER);
        }});
        userExcludes.setGenders(new ArrayList<Gender>() {{
            add(Gender.MALE);
            add(Gender.FEMALE);
        }});
        userExcludes.setReligions(new ArrayList<Religion>() {{
            add(Religion.NONRELIGIOUS);
            add(Religion.PRIMAL);
        }});
        userExcludes.setSalaryRanges(new ArrayList<SalaryRange>() {{
            add(SalaryRange.STARVINGARTIST);
            add(SalaryRange.DADDYWARBUCKS);
        }});
        userExcludes.setStatus(new ArrayList<Status>() {{
            add(Status.MARRIED);
        }});
        return userExcludes;
    }

    private static UserNotificationPreferences CreateUserNotificationPreferences() {
        UserNotificationPreferences userNotificationPreferences = new UserNotificationPreferences();

        userNotificationPreferences.setFavorite(true);
        userNotificationPreferences.setFriendRequestAccepted(true);
        userNotificationPreferences.setFriendRequests(true);
        userNotificationPreferences.setLikes(true);
        userNotificationPreferences.setMessages(true);
        userNotificationPreferences.setPhotoKeyAccept(true);
        userNotificationPreferences.setPhotoKeyRequest(true);
        userNotificationPreferences.setRecRecommendation(true);
        userNotificationPreferences.setViews(true);
        userNotificationPreferences.setWasRecommended(true);

        return userNotificationPreferences;
    }

    //endregion Create User Helpers

    //region Create Sample Interactions
    private static void CreateUserInteractions() {
        //Messsages
        CreateUserInteractionMessages();

//        userInteractions.setBlocked();
//        userInteractions.setFavorites();
//        userInteractions.setFriendRequests();
//        userInteractions.setFriends();
//        userInteractions.setLikes();
//        userInteractions.setMatches();
//        userInteractions.setMyPrivatePhotos();
//        userInteractions.setPrivatePhotoAccess();
//        userInteractions.setReceivedGifts();
//        userInteractions.setRecommendees();
//        userInteractions.setRecommenders();
//        userInteractions.setSentGifts();
//        userInteractions.setViewed();
    }

    private static void CreateUserInteractionMessages() {
        //Messsages

        ArrayList<Message> in0 = new ArrayList<>();
        ArrayList<Message> out0 = new ArrayList<>();
        ArrayList<Message> in1 = new ArrayList<>();
        ArrayList<Message> out1 = new ArrayList<>();
        ArrayList<Message> in2 = new ArrayList<>();
        ArrayList<Message> out2 = new ArrayList<>();

        Message m1 = CreateMessage(users.get(1), users.get(0), "I have no personality! Want to date?", "How are ya, sweetie?", false, null);
        Message m2 = CreateMessage(users.get(0), users.get(1), "I have no personality! Want to date?", "Get a personality, we'll talk", false, m1);
        Message m3 = CreateMessage(users.get(0), users.get(1), "I can't believe you're single! You're so hot!", "OMG, are you single?!", false, null);
        Message m4 = CreateMessage(users.get(1), users.get(0), "I can't believe you're single! You're so hot!", "Go fish!", false, m3);
        Message m5 = CreateMessage(users.get(2), users.get(0), "I've had enough of your tomfoolery. Purchase me dinner!", "Buy me dinner or you will be sorry.", false, null);
        Message m6 = CreateMessage(users.get(0), users.get(2), "I've had enough of your tomfoolery. Purchase me dinner!", "You're on!", false, m5);

        out0.add(m1);
        in1.add(m1);

        out1.add(m2);
        in0.add(m2);

        out1.add(m3);
        in0.add(m3);

        out0.add(m4);
        in1.add(m4);

        out0.add(m5);
        in2.add(m5);

        out2.add(m6);
        in0.add(m6);

        users.get(0).getInteractions().setInBox(in0);
        users.get(0).getInteractions().setOutBox(out0);

        users.get(1).getInteractions().setInBox(in1);
        users.get(1).getInteractions().setOutBox(out1);

        users.get(2).getInteractions().setInBox(in2);
        users.get(2).getInteractions().setOutBox(out2);
    }
    //endregion Create Sample Interactions


    //region Helper helpers ...
    private static ArrayList<ZipCode> CreateZipCodes() {
        if (zipCodes != null)
            return zipCodes;

        zipCodes = new ArrayList<ZipCode>() {{
            add(new ZipCode("73012", 35.733957, -97.577310, "Edmond, OK"));
            add(new ZipCode("73013", 35.618788, -97.484375, "Edmond, OK"));
            add(new ZipCode("73025", 35.667234, -97.593657, "Edmond, OK"));
            add(new ZipCode("73034", 35.703414, -97.434052, "Edmond, OK"));
            add(new ZipCode("73003", 35.668905, -97.497380, "Edmond, OK"));
        }};
        return zipCodes;
    }

    private static Message CreateMessage(User to, User from, String subjectText, String messageText, boolean read, Message replyToMessage) {
        Message message = new Message();
        message.setTo(to);
        message.setFrom(from);
        message.setMessage(messageText);
        message.setSubject(subjectText);
        message.setRead(read);
        message.setReplyToMessage(replyToMessage);
        return message;
    }
    //endregion Helper helpers ...

    public static void setObjectStreams(String filename) {
        mFilename = filename;
    }


    public static void WriteModel(ServiceProxy appService) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(mFilename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(appService);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static AppService ReadModel() {
        AppService appService = null;


        try {
            FileInputStream inputStream = new FileInputStream(mFilename);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            appService = (AppService) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return appService;
    }


}
