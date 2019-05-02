package me.filipebezerra.cms.domain.exceptions;

import lombok.Getter;

public class CategoryNotFoundException extends RuntimeException {

    @Getter
    private final String id;

    public CategoryNotFoundException(final String id) {
        this.id = id;
    }
}
