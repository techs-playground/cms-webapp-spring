package me.filipebezerra.cms.domain.exceptions;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException {

    @Getter
    private final String id;

    public UserNotFoundException(final String id) {
        this.id = id;
    }

}
