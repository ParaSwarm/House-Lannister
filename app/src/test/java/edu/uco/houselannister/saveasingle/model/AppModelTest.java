package edu.uco.houselannister.saveasingle.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Questionnaire;
import edu.uco.houselannister.saveasingle.service.AppService;

import static org.junit.Assert.*;

/**
 * Created by Gordon on 9/25/2016.
 */
public class AppModelTest {

    Model appModel;

    @Before
    public void setUp() throws Exception {
        this.appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
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

    }

    @Test
    public void testGetUserResponse() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testSaveCurrentUser() throws Exception {

    }

    @Test
    public void testGetUsers() throws Exception {

    }

    @Test
    public void testGetUsernameArray() throws Exception {

    }

    @Test
    public void testGetUsernameMap() throws Exception {

    }
}