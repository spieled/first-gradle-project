package com.mgj.base;

/**
 * Created by yanqu on 2016/6/15.
 */
public enum Gender implements BaseEnum {

    MALE("男"),
    FEMALE("女"),
    UNKNOWN("保密")
    ;
    private String display;
    Gender(String display) {
        this.display = display;
    }

    @Override
    public String display() {
        return this.display;
    }
}
