package com.aquino.mypersonalapp.repository;

import com.aquino.mypersonalapp.model.User;
import com.orm.SugarRecord;
import java.util.List;

public class UserRepository {


    static{

        User u1 = new User("ebenites", "tecsup", "Erick Benites");

        User u2 = new User("jfarfan", "tecsup", "Jaime Farfán");

        User u3 = new User("drodriguez", "tecsup", "David Rodriguez");
        SugarRecord.save(u1);
        SugarRecord.save(u2);
        SugarRecord.save(u3);
    }



    public static User login(String username, String password){

     List<User> usuarios = SugarRecord.findWithQuery(User.class, "Select * FROM USER WHERE username= '" + username + "'");


        if(!usuarios.isEmpty()) {
            for (User user : usuarios) {
                if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                    return user;
                }else if(user.getUsername().equalsIgnoreCase(username) && !user.getPassword().equals(password)) {
                    return null;
                }
            }
        }else{
               User user = new User(username, password, username);
                SugarRecord.save(user);
                return user;
        }
        return null;
    }

    public static void update(String username, String fullname){
        List<User> usuarios = SugarRecord.findWithQuery(User.class, "Select * FROM USER WHERE username= '" + username + "'");
        for (User user : usuarios){
            if(user.getUsername().equalsIgnoreCase(username)){
                user.setFullname(fullname);
                SugarRecord.save(user);
            }
        }

    }

}
