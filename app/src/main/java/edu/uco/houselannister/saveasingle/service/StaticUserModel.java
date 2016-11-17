package edu.uco.houselannister.saveasingle.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

import edu.uco.houselannister.saveasingle.domain.Bio;
import edu.uco.houselannister.saveasingle.domain.EducationLevel;
import edu.uco.houselannister.saveasingle.domain.Ethnicity;
import edu.uco.houselannister.saveasingle.domain.Gender;
import edu.uco.houselannister.saveasingle.domain.Interests;
import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.Personality;
import edu.uco.houselannister.saveasingle.domain.Photo;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.QuestionCategory;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.domain.Relationship;
import edu.uco.houselannister.saveasingle.domain.Religion;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.domain.SalaryRange;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.domain.Status;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.domain.UserDemographics;
import edu.uco.houselannister.saveasingle.domain.UserNotificationPreferences;
import edu.uco.houselannister.saveasingle.domain.UserPreferences;
import edu.uco.houselannister.saveasingle.domain.UserStatus;
import edu.uco.houselannister.saveasingle.domain.ZipCode;

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
            /*  Gender : male = 0, female = 1
                Education: 0 = null, 1 = WHo cares, 2 = high school, 3= Undergrad , 4 = Masters, 5 = Masters , 6 = Phd
                Smoking : 0= null, 1 = Never, 2 = 1-5/day, 3 = 6-10, 4 = 10 - 20 , 5 = more than 20
                Body Type : 0 = null, 1 = Slim , 2 = Average , 3 = Muscular , 4 = Slightly fat
                Income : 0 = null, 1 = Unemployeed, 2 = < 20 K,3 = < 100 K, 4 = 100 K , 5 = >100 K
                Married Status : 0 = null, 1 = not married , 2= married , 3 = divorced


             */
            users.add(CreateUser("Jackson", false, "password", "jackson@uco.edu",20, 0,5.9,2,"Christian","Black",5,1,"Cashier",2,1,0,"I am a hansome looking bachlor. I am 20 and love playing playstation 4, eating different types of food, and travelling.","I am looking for a date yo ladies", "Jackson Smith", true));
            users.add(CreateUser("Sierra", false, "password", "sierra@uco.edu",22, 1,5.4,3,"Christian","White",1,1,"Unemployeed",1,1,0,"Hello everyone, I am Sierra. I am a student.","I am looking for a date yo boys", "Sierra Rose", false));
            users.add(CreateUser("Goliath", true, "password", "goliath@gmail.com",0,0,0.0,0,"","",0,0,"",0,0,0,"","","", true));
            users.add(CreateUser("Qaiser", true, "password", "qaiser@uco.edu",19,0,5.7,
                                    4,"Muslim","Arabic",3,2,"Accountant",4,1,0,
                                    "I am a Good boy","I need a good girl","Qaiser Ali", true));

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
    private static User CreateUser(String username, Boolean isAdmin, String userPassword, String userEmail, int age, int gender,
                                   Double height, int education, String religion,
                                   String Ethnicity, int Smoking, int BodyType, String Work, int Income,
                                   int MarriedStatus, int numofChildren, String Story, String PerfectMatch,String fullname , Boolean enabled) {

        User user = new User();

        user.setName(username);
        user.setAdmin(isAdmin);
        user.setEmailAddress(userEmail);
        user.setPassword(userPassword);
        user.setAge(age);
        user.setGender(gender);
        user.setHeight(height);
        user.setEducation(education);
        user.setReligion(religion);
        user.setEthnicity(Ethnicity);
        user.setSmoking(Smoking);
        user.setBodyType(BodyType);
        user.setWork(Work);
        user.setIncome(Income);
        user.setMarriedStatus(MarriedStatus);
        user.setChildren(numofChildren);
        user.setStory(Story);
        user.setPerfectMatch(PerfectMatch);
        user.setFullName(fullname);
        //user.setProfilePhoto(profilePhoto);

        user.setEnabled(enabled);

        user.setBio(CreateBio());
        user.setStatus(CreateStatus());

        ArrayList<Response> responses = new ArrayList<>();
        responses.add(CreateResponse(0, "I like my teeth", 0, 0, 1));
        responses.add(CreateResponse(1, "People should vote", 0, 0, 1, 2));
        user.setQuestionResponses(responses);

        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(CreatePhoto(false));
        photos.add(CreatePhoto(true));
        user.setPhotos(photos);
        user.setProfilePhoto(photos.get(0));

        user.setUserDemographics(CreateUserDemographics());
        user.setUserPreferences(CreateUserPreferences());
        user.setUserExcludes(CreateUserExcludes());

        user.setUserNotificationPreferences(CreateUserNotificationPreferences());

        return user;
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

    private static UserStatus CreateStatus() {
        UserStatus status = new UserStatus();

        // Set last online time randomly in the last 3 days
        int random = new Random().nextInt(259200) + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -random);
        status.setLastOnlineDate(calendar.getTime());

        return status;
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
        //Messages
        CreateUserInteractionMessages();
        CreateUserFavorites();

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
        //Messages

        ArrayList<Message> inbox0 = new ArrayList<>();
        ArrayList<Message> outbox0 = new ArrayList<>();
        ArrayList<Message> inbox1 = new ArrayList<>();
        ArrayList<Message> outbox1 = new ArrayList<>();
        ArrayList<Message> inbox2 = new ArrayList<>();
        ArrayList<Message> outbox2 = new ArrayList<>();

        Message message1 = CreateMessage(users.get(1), users.get(0), "I have no personality! Want to date?", "How are ya, sweetie?", false, null);
        Message message2 = CreateMessage(users.get(0), users.get(1), "I have no personality! Want to date?", "Get a personality, we'll talk", false, message1);
        Message message3 = CreateMessage(users.get(0), users.get(1), "I can't believe you're single! You're so hot!", "OMG, are you single?!", false, null);
        Message message4 = CreateMessage(users.get(1), users.get(0), "I can't believe you're single! You're so hot!", "Go fish!", false, message3);
        Message message5 = CreateMessage(users.get(2), users.get(0), "I've had enough of your tomfoolery. Purchase me dinner!", "Buy me dinner or you will be sorry.", false, null);
        Message message6 = CreateMessage(users.get(0), users.get(2), "I've had enough of your tomfoolery. Purchase me dinner!", "You're on!", false, message5);
        Message message7 = CreateMessage(users.get(2), users.get(1), "You're scaring me!", "Please stop asking me about my cat.", false, null);

        outbox0.add(message1);
        inbox1.add(message1);

        outbox1.add(message2);
        inbox0.add(message2);

        outbox1.add(message3);
        inbox0.add(message3);

        outbox0.add(message4);
        inbox1.add(message4);

        outbox0.add(message5);
        inbox2.add(message5);
        inbox2.add(message7);

        outbox2.add(message6);
        inbox0.add(message6);

        users.get(0).getInteractions().setInBox(inbox0);
        users.get(0).getInteractions().setOutBox(outbox0);

        users.get(1).getInteractions().setInBox(inbox1);
        users.get(1).getInteractions().setOutBox(outbox1);

        users.get(2).getInteractions().setInBox(inbox2);
        users.get(2).getInteractions().setOutBox(outbox2);
    }
    //endregion Create Sample Interactions

    private static void CreateUserFavorites() {
        ArrayList<User> favorite0 = new ArrayList<>();
        ArrayList<User> favorite1 = new ArrayList<>();
        ArrayList<User> favorite2 = new ArrayList<>();

        favorite0.add(users.get(1));
        users.get(0).getInteractions().setFavorites(favorite0);
        users.get(1).getInteractions().setFavorites(favorite1);
        favorite2.add(users.get(0));
        favorite2.add(users.get(1));
        users.get(2).getInteractions().setFavorites(favorite2);

    }

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
        if (mFilename == null) return;
        FileOutputStream fileOutputStream = new FileOutputStream(mFilename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(appService);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static AppService ReadModel() {
        if (mFilename == null) return null;

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
