package com.example.mylogin;

public class User {
    public String first_name,last_name,email,password;
    //empty constructor
    public User(){}
    // loaded constructor
    public User(String first_name,String last_name,String email,String password){

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }
}
