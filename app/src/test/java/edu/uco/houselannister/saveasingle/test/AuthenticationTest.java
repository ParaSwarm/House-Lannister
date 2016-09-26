package edu.uco.houselannister.saveasingle.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uco.houselannister.saveasingle.domain.Authentication;

import static org.junit.Assert.*;

/**
 * Created by Gordon on 9/25/2016.
 */
public class AuthenticationTest {
    Authentication authentication;

    @Before
    public void setUp() throws Exception {

        authentication = new Authentication() {
            @Override
            public void Authenticate(String username, String password) {

            }

            @Override
            public Boolean isUser() {
                return true;
            }

            @Override
            public Boolean isAdmin() {
                return false;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        assertEquals(true, authentication.isUser());
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
}