package com.maitaidan.model;

/**
 * Created by Crytis on 2016/5/23.
 * Just test.
 */
public enum MethodEnum {
    POST(1),
    GET(2);

    private int code;

    MethodEnum(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }
}
