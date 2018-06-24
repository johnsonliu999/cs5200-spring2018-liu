package edu.northeastern.cs5200.cs5200spring2018liu.models;

import java.util.Collection;
import java.sql.Date;
import java.util.LinkedList;

public class Developer extends Person {
    private String developerKey;
    private Collection<Website> websites;

    public Developer(int id, String firstName, String lastName, String username, String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses, String developerKey, Collection<Website> websites) {
        super(id, firstName, lastName, username, password, email, dob, phones, addresses);
        this.developerKey = developerKey;
        if (websites == null) this.websites = new LinkedList<>();
        else this.websites = websites;
    }

    public String getDeveloperKey() {
        return developerKey;
    }

    public void setDeveloperKey(String developerKey) {
        this.developerKey = developerKey;
    }

    public Collection<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(Collection<Website> websites) {
        this.websites = websites;
    }


    @Override
    public String toString() {
        return "Developer{" +
                "developerKey='" + developerKey + '\'' +
                ", websites=" + websites +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // for super class
        if (!super.equals(o)) return false;

        Developer developer = (Developer) o;

        if (developerKey != null ? !developerKey.equals(developer.developerKey) : developer.developerKey != null)
            return false;
        return websites != null ? websites.equals(developer.websites) : developer.websites == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (developerKey != null ? developerKey.hashCode() : 0);
        result = 31 * result + (websites != null ? websites.hashCode() : 0);
        return result;
    }
}
