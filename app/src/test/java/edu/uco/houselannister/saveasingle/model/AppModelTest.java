package edu.uco.houselannister.saveasingle.model;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import edu.uco.houselannister.saveasingle.domain.Interests;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Personality;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.domain.Relationship;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.domain.ZipCode;
import edu.uco.houselannister.saveasingle.service.AppService;

import static org.junit.Assert.*;

/**
 * Created by Gordon on 9/25/2016.
 */
public class AppModelTest {

    Model appModel;
    User testUserJoe;

    @Before
    public void setUp() throws Exception {
        this.appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        testUserJoe = new User() {
            {
                setName("Joe");
                setAdmin(true);
                setEmailAddress("joe@gmail.com");
                setPassword("thepass");
                getUserDemographics().setMyZipCode(new ZipCode("83838", 97.32, -37.4, "somwhere, ok"));
                getUserDemographics().setPersonalityTags(new ArrayList<Personality>() {{
                    add(Personality.INSIGHTFUL);
                    add(Personality.THOUGHTFUL);
                }});
                getUserDemographics().setRelationshipTags(new ArrayList<Relationship>() {
                    {
                        add(Relationship.INTIMATE);
                        add(Relationship.SERIOUS);
                    }
                });
            }
        };
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetAppModelInstance() throws Exception {
        assertNotNull(this.appModel);
    }

    @Test
    public void testAuthenticate() throws Exception {

        this.appModel.Authenticate("jackson@uco.edu", "password");
        assertTrue("User failed authentication", appModel.isUser());

        this.appModel.Authenticate("jackson@uco.edu", "wrong");
        assertTrue("User should not be authentication", !appModel.isUser());
    }

    @Test
    public void testIsUser() throws Exception {

        this.appModel.Authenticate("jackson@uco.edu", "password");
        assertTrue("User failed authentication", appModel.isUser());

        this.appModel.Authenticate("jackson@uco.edu", "wrongPassword");
        assertTrue("User should not be authentication", !appModel.isUser());
    }

    @Test
    public void testIsAdmin() throws Exception {

        this.appModel.Authenticate("goliath@gmail.com", "password");
        assertTrue("User is an admin", appModel.isAdmin());


        this.appModel.Authenticate("jackson@uco.edu", "password");
        assertTrue("User is not an admin", !appModel.isAdmin());

    }

    @Test
    public void testGetQuestionnaire() throws Exception {
        Questionnaire q = this.appModel.getQuestionnaire();
        assertNotNull(q);

    }

    @Test
    public void testGetAuthenticatedUser() throws Exception {
        String email = "goliath@gmail.com";
        String password = "password";
        this.appModel.Authenticate(email, password);
        assertEquals(appModel.getAuthenticatedUser().getEmailAddress(), email);


        this.appModel.Authenticate("jackson@uco.edu", "password");
        assertNotEquals(appModel.getAuthenticatedUser().getEmailAddress(), email);

    }

    @Test
    public void testGetUserResponses() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetUserResponse() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetUser() throws Exception {
        User u = appModel.getUser("Goliath");
        assertEquals("User email retreived", "goliath@gmail.com", u.getEmailAddress());
    }

    @Test
    public void testSaveCurrentUser() throws Exception {
        User u = appModel.getUser("Goliath");
        String reset = u.getEmailAddress();
        u.setEmailAddress("shonuff");
        appModel.saveUser(u);

        u = null;
        u = appModel.getUser("Goliath");
        assertEquals("Email should be shonuff", "shonuff", u.getEmailAddress());

        u.setEmailAddress(reset);
        appModel.saveUser(u);
        assertEquals("Email should be reset", "goliath@gmail.com", u.getEmailAddress());

    }

    @Test
    public void testGetUsers() throws Exception {
        assertEquals("Should be 3 users", 3, appModel.getUsers().size());
    }

    @Test
    public void testGetUsernameArray() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetUsernameMap() throws Exception {

        String testString = null;
        appModel.getUsers();
        ArrayList<HashMap<String, String>> testUnit = appModel.getUsernameMap();
        for (HashMap<String, String> h : testUnit) {
            testString = h.get("Jackson");
            if (testString != null) {
                assertEquals("User should be found and the email should match.", "jackson@uco.edu", testString);
                break;
            }
        }
        assertNotNull("String should not be null", testString);
    }

    @Test
    public void testGetCurrentUser() throws Exception {
        // Baseline
        appModel.Authenticate("goliath@gmail.com", "password");
        User au = appModel.getAuthenticatedUser();
        User cu = appModel.getCurrentUser();
        User mu = appModel.getUser("Jackson");
        assertEquals("Authenticated user and Impersonated user should be same.", au, cu );
        assertNotEquals("Model user and CurrentImpersonated user should not be the same.", mu, cu );

        // Impersonate Jackson
        appModel.setCurrentUserImpersonation(mu);
        cu = appModel.getCurrentUser();
        assertEquals("Model user and CurrentImpersonated user should be same.", mu, cu );
        assertNotEquals("Authenticated user and CurrentImpersonated user should not be same.", au, cu );

        // Go back to baseline
        appModel.resetCurrentUserImpersonation();
        cu = appModel.getCurrentUser();
        assertEquals("Authenticated user and CurrentImpersonated user should be same.", au, cu );
        assertNotEquals("Model user and CurrentImpersonated user should not be the same.", mu, cu );
    }

    @Test
    public void testSetCurrentUserImpersonation() throws Exception {

        /*
        * Authenticated user is the currently logged in user.
        * The impersonated user is the user interacting with the application.
        * Most of the time, the authenticated user and the impersonated user
        * are the same.
        *
        * In Administrative cases, the admin can impersonate a user and use
        * the application as the user.  This is for modifications and corrections
        * that the admins may need to do to fix problems.
        * */

        // Baseline
        appModel.Authenticate("goliath@gmail.com", "password");
        User au = appModel.getAuthenticatedUser();
        User cu = appModel.getCurrentUser();
        User mu = appModel.getUser("Jackson");
        assertEquals("Authenticated user and Impersonated user should be same.", au, cu );
        assertNotEquals("Model user and CurrentImpersonated user should not be the same.", mu, cu );

        // Impersonate Jackson
        appModel.setCurrentUserImpersonation(mu);
        cu = appModel.getCurrentUser();
        assertEquals("Model user and CurrentImpersonated user should be same.", mu, cu );
        assertNotEquals("Authenticated user and CurrentImpersonated user should not be same.", au, cu );

        // Go back to baseline
        appModel.resetCurrentUserImpersonation();
        cu = appModel.getCurrentUser();
        assertEquals("Authenticated user and CurrentImpersonated user should be same.", au, cu );
        assertNotEquals("Model user and CurrentImpersonated user should not be the same.", mu, cu );
    }

    @Test
    public void testResetCurrentUserImpersonation() throws Exception {
        appModel.Authenticate("goliath@gmail.com", "password");
        User au = appModel.getAuthenticatedUser();
        User cu = appModel.getCurrentUser();
        User mu = appModel.getUser("Jackson");
        assertEquals("Authenticated user and Impersonated user should be same.", au, cu );
        assertNotEquals("Model user and CurrentImpersonated user should not be the same.", mu, cu );

        // Impersonate Jackson
        appModel.setCurrentUserImpersonation(mu);
        cu = appModel.getCurrentUser();
        assertEquals("Model user and CurrentImpersonated user should be same.", mu, cu );
        assertNotEquals("Authenticated user and CurrentImpersonated user should not be same.", au, cu );

        // Go back to baseline
        appModel.resetCurrentUserImpersonation();
        cu = appModel.getCurrentUser();
        assertEquals("Authenticated user and CurrentImpersonated user should be same.", au, cu );
        assertNotEquals("Model user and CurrentImpersonated user should not be the same.", mu, cu );
    }

    @Test
    public void testUserCrud() throws Exception {
        assertEquals(3, appModel.getUsers().size());
        testAddUser();
        assertEquals(4, appModel.getUsers().size());
        testSaveUser_Password();
        assertEquals(4, appModel.getUsers().size());
        testSaveUser_Deeper();
        assertEquals(4, appModel.getUsers().size());
        testDeleteUser();
        assertEquals(3, appModel.getUsers().size());
    }


    public void testAddUser() throws Exception {
        appModel.saveUser(testUserJoe);
        assertEquals(4, appModel.getUsers().size());
        assertEquals("thepass", testUserJoe.getPassword());
    }

    public void testSaveUser_Password() throws Exception {
        testUserJoe.setPassword("theotherpass");
        appModel.saveUser(testUserJoe);

        assertEquals(4, appModel.getUsers().size());
        String actualPass = "theotherpass";
        String testPass = appModel.getUser("Joe").getPassword();
        assertEquals(actualPass, testPass);

    }

    public void testSaveUser_Deeper() throws Exception {

        Personality i = testUserJoe.getUserDemographics().getPersonalityTags().get(testUserJoe.getUserDemographics().getPersonalityTags().size() - 1);
        assertEquals(Personality.THOUGHTFUL, i);
        testUserJoe.getUserDemographics().getPersonalityTags().set(testUserJoe.getUserDemographics().getPersonalityTags().size() - 1, Personality.EXTROVERT);
        appModel.saveUser(testUserJoe);
        i = testUserJoe.getUserDemographics().getPersonalityTags().get(testUserJoe.getUserDemographics().getPersonalityTags().size() - 1);
        assertEquals(Personality.EXTROVERT, i);

        assertEquals(4, appModel.getUsers().size());
        String actualPass = "theotherpass";
        String testPass = appModel.getUser("Joe").getPassword();
        assertEquals(actualPass, testPass);

    }

    public void testDeleteUser() throws Exception {
        appModel.deleteUser(testUserJoe);
        assertEquals(3, appModel.getUsers().size());
    }
}