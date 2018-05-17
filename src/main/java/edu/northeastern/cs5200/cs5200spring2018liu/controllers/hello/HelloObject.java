package edu.northeastern.cs5200.cs5200spring2018liu.controllers.hello;

public class HelloObject {
    private String message;

    public HelloObject() {
    }

    public HelloObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
