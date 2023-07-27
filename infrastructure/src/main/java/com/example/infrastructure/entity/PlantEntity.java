package com.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    @ManyToMany
    private List<BotanicDetailEntity> BotanicDetails;
    private int growProgression;
    private int plantLevel;
    @ManyToOne
    private UserEntity user;
    private LocalDate lastWatering;
}
