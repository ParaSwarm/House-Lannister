package edu.uco.houselannister.saveasingle.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uco.houselannister.saveasingle.domain.ServiceProxy;

import static org.junit.Assert.*;

/**
 * Created by Gordon on 9/25/2016.
 */
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

    }

    @Test
    public void testSaveUser() throws Exception {

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
    public void testCreateAppService1() throws Exception {

    }
}