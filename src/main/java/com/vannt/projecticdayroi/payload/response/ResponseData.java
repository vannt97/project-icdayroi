package com.vannt.projecticdayroi.payload.response;

public class ResponseData {
    private int status;
    private Object data;
    private boolean isSucces;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSucces() {
        return isSucces;
    }

    public void setSucces(boolean succes) {
        isSucces = succes;
    }
}
