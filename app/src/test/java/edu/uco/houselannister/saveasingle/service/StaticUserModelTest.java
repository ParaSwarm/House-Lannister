package edu.uco.houselannister.saveasingle.service;

import org.junit.Test;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.domain.User;

import static org.junit.Assert.*;

/**
 * Created by Gordon on 9/25/2016.
 */
public class StaticUserModelTest {

    StaticUserModel staticUserModel;
    @Test
    public void testUsers() throws Exception {

        ArrayList<User> users = this.staticUserModel.Users();
        assertEquals(users.size(),4);
    }
}