package edu.uco.houselannister.saveasingle.Service;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.Bio;
import edu.uco.houselannister.saveasingle.domain.Photo;
import edu.uco.houselannister.saveasingle.domain.Question;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.domain.Response;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.domain.UserDemographics;
import edu.uco.houselannister.saveasingle.domain.UserInteractions;
import edu.uco.houselannister.saveasingle.domain.UserNotificationPreferences;
import edu.uco.houselannister.saveasingle.domain.UserPreferences;

public class StaticUserModel {

    public static ArrayList<User> Users() {
        return CreateUsers();
    }

    private static ArrayList<User> CreateUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(CreateUser("numberOne", true, "abc123", "someoneOne@somewhere.com"));
        users.add(CreateUser("numberTwo", true, "abc123", "someoneTwo@somewhere.com"));
        users.add(CreateUser("numberThree", true, "abc123", "someoneThree@somewhere.com"));
        return users;
    }

    private static User CreateUser(
            String username
            , Boolean isAdmin
            , String userPassword
            , String userEmail) {

        User ret = new User();

        ArrayList<Response> responses = new ArrayList<>();
        ArrayList<Photo> photos = new ArrayList<>();

        responses.add(CreateResponse());
        responses.add(CreateResponse());

        photos.add(CreatePhoto());
        photos.add(CreatePhoto());

        ret.setName(username);
        ret.setAdmin(isAdmin);
        ret.setEmailAddress(userEmail);
        ret.setPassword(userPassword);

        ret.setBio(CreateBio());
        ret.setUserDemographics(CreateUserDemographics());
        ret.setInteractions(CreateUserInteractions());
        ret.setUserPreferences(CreateUserPreferences());
        ret.setUserNotificationPreferences(CreateUserNotificationPreferences());
        ret.setUserExcludes(CreateUserExcludes());
        ret.setPhotos(photos);
        ret.setQuestionResponses(responses);

        ret.setProfilePhoto(photos.get(0));

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

    private static Response CreateResponse() {
        Response response = new Response();
        return response;
    }

    private static Photo CreatePhoto() {
        Photo photo = new Photo();
        return photo;
    }

    private static Bio CreateBio() {
        Bio bio = new Bio();
        bio.setAboutMe("This is stuff about me.");
        bio.setAboutYou("This is stuff about you.");
        bio.setWhyMessageMe("This is why you should message me.");
        return bio;
    }

    private static Questionnaire CreateQuestionnaire() {
        Questionnaire questionnaire = new Questionnaire();
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(CreateQuestion());
        questionnaire.setQuestions(questions);
        return questionnaire;
    }

    private static Question CreateQuestion() {
        Question question = new Question();
        return question;
    }
}
