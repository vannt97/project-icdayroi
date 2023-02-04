package com.vannt.projecticdayroi.model;

import com.vannt.projecticdayroi.jwt.TypeToken;

public class SubjectDataModel {
    private String email;
    private TypeToken typeToken;

    public SubjectDataModel(String email, TypeToken typeToken) {
        this.email = email;
        this.typeToken = typeToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeToken getTypeToken() {
        return typeToken;
    }

    public void setTypeToken(TypeToken typeToken) {
        this.typeToken = typeToken;
    }
}
