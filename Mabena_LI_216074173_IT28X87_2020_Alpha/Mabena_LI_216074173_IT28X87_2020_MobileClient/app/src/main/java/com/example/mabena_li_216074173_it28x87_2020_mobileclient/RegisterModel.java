package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

public class RegisterModel {

    String user_id;
    String user_name;
    String user_title;
    String user_emailaddress;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    String user_password;
    String user_confirmpassword;
    String error;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public String getUser_emailaddress() {
        return user_emailaddress;
    }

    public void setUser_emailaddress(String user_emailaddress) {
        this.user_emailaddress = user_emailaddress;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_confirmpassword() {
        return user_confirmpassword;
    }

    public void setUser_confirmpassword(String user_confirmpassword) {
        this.user_confirmpassword = user_confirmpassword;
    }
}
