package com.vannt.projecticdayroi.payload.response;

public class DataTokenResponse {
    private String token;
    private String refeshToken;

    public DataTokenResponse(String token, String refeshToken) {
        this.token = token;
        this.refeshToken = refeshToken;
    }

    public DataTokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefeshToken() {
        return refeshToken;
    }

    public void setRefeshToken(String refeshToken) {
        this.refeshToken = refeshToken;
    }
}
