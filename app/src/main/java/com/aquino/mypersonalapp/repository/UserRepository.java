package com.aquino.mypersonalapp.repository;

import com.aquino.mypersonalapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno on 9/10/2017.
 */

public class UserRepository {

    public static List<User> users = new ArrayList<>();

    static{
        users.add(new User( "ebenites", "tecsup", "Erick Benites"));
        users.add(new User("jfarfan", "tecsup", "Jaime Farfán"));
        users.add(new User( "drodriguez", "tecsup", "David Rodriguez"));
    }

    public static User login(String username, String password){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }else{
               user= new User(username, password, username);
                users.add(user);
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

    public static User agregarUsuario(String username, String password){

        User user= new User(username, password, username);
        users.add(user);

        return user;
    }

}
