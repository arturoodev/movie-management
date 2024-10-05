package org.arturocode.moviemanagement.exception;

import lombok.Getter;

@Getter
public class InvalidPasswordException extends RuntimeException {

    private final String password;
    private final String confirmPassword;
    private final String errorDescription;

    public InvalidPasswordException(String password, String errorDescription) {
        this.password = password;
        this.confirmPassword = password;
        this.errorDescription = errorDescription;
    }

    public InvalidPasswordException(String password, String confirmPassword, String errorDescription) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.errorDescription = errorDescription;
    }

    @Override
    public String getMessage() {
        return "Invalid password: " + errorDescription;
    }

}
