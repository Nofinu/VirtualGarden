package org.example.entity;

import java.time.LocalDate;
import java.util.List;

public class Plant {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private List<BotanicDetail> BotanicDetails;
    private int growProgression;
    private int plantLevel;
    private User user;
    private LocalDate lastWatering;

    public Plant() {
    }

    public Plant(String name, String description, String imageUrl, List<BotanicDetail> botanicDetails,User user) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        BotanicDetails = botanicDetails;
        this.growProgression = 0;
        this.plantLevel = 0;
        this.user = user;
    }

    public Plant(int id, String name, String description, String imageUrl, List<BotanicDetail> botanicDetails, int growProgression, int plantLevel,User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        BotanicDetails = botanicDetails;
        this.growProgression = growProgression;
        this.plantLevel = plantLevel;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<BotanicDetail> getBotanicDetails() {
        return BotanicDetails;
    }

    public void setBotanicDetails(List<BotanicDetail> botanicDetails) {
        BotanicDetails = botanicDetails;
    }

    public int getGrowProgression() {
        return growProgression;
    }

    public void setGrowProgression(int growProgression) {
        this.growProgression = growProgression;
    }

    public int getPlantLevel() {
        return plantLevel;
    }

    public void setPlantLevel(int plantLevel) {
        this.plantLevel = plantLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(LocalDate lastWatering) {
        this.lastWatering = lastWatering;
    }
}
