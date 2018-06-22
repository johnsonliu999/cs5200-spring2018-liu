package edu.northeastern.cs5200.cs5200spring2018liu.models;

import java.sql.Date;
import java.util.Collection;

abstract public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Date dob;
    private Collection<Phone> phones;
    private Collection<Address> addresses;

    public Person(int id, String firstName, String lastName, String username, String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.phones = phones;
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", phones=" + phones +
                ", addresses=" + addresses +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null)
            return false;
        if (username != null ? !username.equals(person.username) : person.username != null)
            return false;
        if (password != null ? !password.equals(person.password) : person.password != null)
            return false;
        if (email != null ? !email.equals(person.email) : person.email != null)
            return false;
        if (dob != null ? !dob.equals(person.dob) : person.dob != null)
            return false;
        if (phones != null ? !phones.equals(person.phones) : person.phones != null)
            return false;
        return addresses != null ? addresses.equals(person.addresses) : person.addresses == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        return result;
    }
}
