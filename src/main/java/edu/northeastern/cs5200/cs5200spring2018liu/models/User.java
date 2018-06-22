package edu.northeastern.cs5200.cs5200spring2018liu.models;

import java.util.Collection;
import java.sql.Date;

public class User extends Person {
    private boolean userAgreement;
    private String userKey;

    public User(int id, String firstName, String lastName, String username, String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses, boolean userAgreement, String userKey) {
        super(id, firstName, lastName, username, password, email, dob, phones, addresses);
        this.userAgreement = userAgreement;
        this.userKey = userKey;
    }

    public boolean isUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(boolean userAgreement) {
        this.userAgreement = userAgreement;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
