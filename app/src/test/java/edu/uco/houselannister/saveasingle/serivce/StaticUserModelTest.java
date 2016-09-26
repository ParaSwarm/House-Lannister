package edu.uco.houselannister.saveasingle.serivce;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

import static org.junit.Assert.*;

/**
 * Created by Gordon on 9/25/2016.
 */
public class StaticUserModelTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetUsers() throws Exception {

        ArrayList<User> users = StaticUserModel.getUsers();
        assertEquals(3, users.size());
    }

    @Test
    public void testGetQuestionnaire() throws Exception {

    }
}