package org.example.entity;

public class Comment {
    private int id;
    private String content;
    private User user;
    private Plant plant;

    public Comment() {
    }

    public Comment(String content, User user, Plant plant) {
        this.content = content;
        this.user = user;
        this.plant = plant;
    }

    public Comment(int id, String content, User user, Plant plant) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.plant = plant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
