package com.conference.entity;

import java.io.Serializable;

/** 'User' Enity is mapped both to project.users & project.user_roles tables*/
public class User implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private UserRole userRole;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  "\nUser ID = " + id +
                "\nName: " + name +
                "\nPassword: " + password +
                "\nRole: " + userRole +
                "\n---------------------------------------------------------------------------------------------------";
    }

    public UserRole getUserRole() {
        return userRole;
    }
}