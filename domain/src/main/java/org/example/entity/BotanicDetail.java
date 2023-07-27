package org.example.entity;

public class BotanicDetail {
    private int id;
    private String detail;

    public BotanicDetail() {
    }

    public BotanicDetail(String detail) {
        this.detail = detail;
    }

    public BotanicDetail(int id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
