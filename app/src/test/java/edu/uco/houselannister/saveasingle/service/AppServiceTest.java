package edu.uco.houselannister.saveasingle.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uco.houselannister.saveasingle.domain.ServiceProxy;

import static org.junit.Assert.*;

public class AppServiceTest {

    ServiceProxy serviceProxy;

    @Before
    public void setUp() throws Exception {
        this.serviceProxy = AppService.getAppServiceInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateAppService() throws Exception {
        assertNotNull(this.serviceProxy);
    }

    @Test
    public void testGetUser() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testSaveUser() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetQuestionnaire() throws Exception {
        assertFalse("Test Method Not Implemented", true);
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
    public void testGetUsers() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetUsernameArray() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetUsernameMap() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testAuthenticate() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testIsUser() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testIsAdmin() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetAuthenticatedUser() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testSaveCurrentUser() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetAppServiceInstance() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testGetCurrentUser() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testSetCurrentUserImpersonation() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testResetCurrentUserImpersonation() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }

    @Test
    public void testDeleteUser() throws Exception {
        assertFalse("Test Method Not Implemented", true);
    }
}