package edu.uco.houselannister.saveasingle.domain;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Application Model interface holds behaviors for the application
 * as it relates to the session with the current user.
 * This Interface will provide actions for users that deal with the
 * application directly and providing access to local instances of
 * model supplied types.
 *
 * (such as determining current user)
 */
public interface Model extends Authentication, Preferences, UserProfile {

    ArrayList<User> getUsers();
    ArrayList<String> getUsernameArray();
    ArrayList<HashMap<String, String>> getUsernameMap();
}
