package jevera.education.booker.exceptions;

public class UserAlreadyExists extends RuntimeException {
    private String login;
    public UserAlreadyExists(String login) {this.login = login;}
}
