package edu.uco.houselannister.saveasingle.serivce;

import java.util.ArrayList;
import java.util.Collections;

import edu.uco.houselannister.saveasingle.domain.*;

public class StaticUserModel {

    private static Questionnaire questionnaire = null;

    private static ArrayList<User> users = null;

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
            users.add(CreateUser("Jackson", false, "password", "jackson@uco.edu"));
            users.add(CreateUser("Sierra", false, "password", "sierra@uco.edu"));
            users.add(CreateUser("Goliath", true, "password", "goliath@gmail.com"));
        }
        return users;
    }

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

    //region Generate Sample Question Responses
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
    //endregion Generate Sample Question Responses


    private static User CreateUser(String username, Boolean isAdmin, String userPassword, String userEmail) {

        User ret = new User();

        ret.setName(username);
        ret.setAdmin(isAdmin);
        ret.setEmailAddress(userEmail);
        ret.setPassword(userPassword);

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
        ret.setUserNotificationPreferences(CreateUserNotificationPreferences());

        ret.setUserExcludes(CreateUserExcludes());
        ret.setInteractions(CreateUserInteractions());

        return ret;
    }

    private static UserNotificationPreferences CreateUserNotificationPreferences() {
        UserNotificationPreferences userNotificationPreferences = new UserNotificationPreferences();
        return userNotificationPreferences;
    }

    private static UserPreferences CreateUserExcludes() {
        UserPreferences userExcludes = new UserPreferences();
        return userExcludes;
    }

    private static UserPreferences CreateUserPreferences() {
        UserPreferences userPreferences = new UserPreferences();
        return userPreferences;
    }

    private static UserInteractions CreateUserInteractions() {
        UserInteractions userInteractions = new UserInteractions();
        return userInteractions;
    }

    private static UserDemographics CreateUserDemographics() {
        UserDemographics userDemographics = new UserDemographics();
        return userDemographics;
    }

    private static Photo CreatePhoto(Boolean isPrivate) {
        Photo photo = new Photo();
        photo.setPrivate(isPrivate);
        // TODO: Implement URI and Blob Storage for images
        return photo;
    }

    private static Bio CreateBio() {
        Bio bio = new Bio();
        bio.setAboutMe("This is stuff about me.");
        bio.setAboutYou("This is stuff about you.");
        bio.setWhyMessageMe("This is why you should message me.");
        return bio;
    }


}
