package com.mindata.hero.exception;

import lombok.Getter;

@Getter
public enum ErrorDescription {
    HERO_NOT_FOUND_EXCEPTION("Hero not found");
    private final String description;

    ErrorDescription(String description) {
        this.description = description;
    }

}
