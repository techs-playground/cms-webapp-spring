package me.filipebezerra.cms.domain.exceptions;

import lombok.Getter;

public class NewsNotFoundException extends RuntimeException {

    @Getter
    private String id;

    public NewsNotFoundException(final String id) {
        this.id = id;
    }

}
