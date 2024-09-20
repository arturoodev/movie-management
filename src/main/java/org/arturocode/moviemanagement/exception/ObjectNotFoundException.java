package org.arturocode.moviemanagement.exception;

import lombok.Getter;

public class ObjectNotFoundException extends RuntimeException {

    @Getter
    private final String objectNotFoundName;
    private final Throwable cause;

    public ObjectNotFoundException(String objectNotFoundName) {
        this.objectNotFoundName = objectNotFoundName;
        this.cause = null;
    }

    public ObjectNotFoundException(String objectNotFoundName, Throwable cause) {
        this.objectNotFoundName = objectNotFoundName;
        this.cause = null;
    }

    @Override
    public String getMessage() {
        return super.getMessage()
                .concat("(object not found: ")
                .concat(this.objectNotFoundName)
                .concat(")");
    }

}
