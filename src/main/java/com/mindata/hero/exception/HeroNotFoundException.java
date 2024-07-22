package com.mindata.hero.exception;

public class HeroNotFoundException extends BaseException {
    public HeroNotFoundException() {
        super(ErrorDescription.HERO_NOT_FOUND_EXCEPTION);
    }
}
