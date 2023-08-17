package com.codewithmuddasir.onlinebanking.helper;

public class ModelImpl implements Model {

    private String userId;

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }
}
