package com.burninglove.dma.burninglove.models;


import java.util.Date;

/**
 * Created by malik on 11/12/2017.
 */

public class Person {
    String username;
    String realName;
    String profilePicture;
    java.util.Date birthday;
    PersonBodyMass bodyMass;

    private Person() {

    }

    public Person(String username, String realName, java.util.Date birthday) {
        this.username = username;
        this.realName = realName;
        this.birthday = birthday;
    }

    public Person(String username, String realName, String profilePicture, java.util.Date birthday) {
        this.username = username;
        this.realName = realName;
        this.profilePicture = profilePicture;
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public PersonBodyMass getBodyMass() {
        return bodyMass;
    }

    public void setBodyMass(PersonBodyMass bodyMass) {
        this.bodyMass = bodyMass;
    }

    public void setBodyMass(float height, float weight) {
        this.bodyMass = new PersonBodyMass(height, weight);
    }
}
