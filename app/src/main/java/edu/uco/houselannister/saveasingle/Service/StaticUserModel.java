package edu.uco.houselannister.saveasingle.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.model.User;

/**
 * Created by Gordon on 9/20/2016.
 */
public class StaticUserModel {

    public static ArrayList<User> Users(){
        ArrayList<User> items = new ArrayList<>();

        for(int i = 0; i < 10; ++i){
            User u = new User();
            u.setName(Character.toString((char) (i + 96)));
            items.add(u);
        }


        return items;
    }
}
