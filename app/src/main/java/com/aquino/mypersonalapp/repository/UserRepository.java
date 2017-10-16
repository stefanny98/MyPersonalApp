package com.aquino.mypersonalapp.repository;

import com.aquino.mypersonalapp.model.User;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno on 9/10/2017.
 */

public class UserRepository {

   // public static List<User> users = new ArrayList<>();
    public static List<User> users = SugarRecord.listAll(User.class);

    static{

        User u1 = new User( "ebenites", "tecsup", "Erick Benites");
        User u2 = new User("jfarfan", "tecsup", "Jaime Farfán");
        User u3 = new User( "drodriguez", "tecsup", "David Rodriguez");

        SugarRecord.save(u1);
        SugarRecord.save(u2);
        SugarRecord.save(u3);
    }



    public static User login(String username, String password){

        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }else{
               user= new User(username, password, username);
                SugarRecord.save(user);
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }

    public static void update(String username, String fullname){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                user.setFullname(fullname);
                SugarRecord.save(user);
            }
        }

    }

}
