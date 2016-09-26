package edu.uco.houselannister.saveasingle.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.service.AppService;

import static org.junit.Assert.*;

/**
 * Created by Gordon on 9/25/2016.
 */
public class AppModelTest {

    AppModel appModel;

    @Before
    public void setUp() throws Exception {
        this.appModel = AppModel.createAppModel(AppService.createAppService());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateAppModel() throws Exception {
        assertNotNull(this.appModel);
    }

    @Test
    public void testAuthenticate() throws Exception {

    }

    @Test
    public void testIsUser() throws Exception {

    }

    @Test
    public void testIsAdmin() throws Exception {

    }

    @Test
    public void testGetQuestionnaire() throws Exception {

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
    public void testGetCurrentUser() throws Exception {

    }
}