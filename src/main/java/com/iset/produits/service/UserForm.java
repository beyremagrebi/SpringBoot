package com.iset.produits.service;

public class UserForm {

        private String username;
        private String password;
        private String confirmedPassword;

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
