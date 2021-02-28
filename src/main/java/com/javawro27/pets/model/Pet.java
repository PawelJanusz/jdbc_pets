package com.javawro27.pets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Pet {

    private Long id;

    private String name;
    private int age;
    private double weight;
    private boolean pureRace;

    private String ownerName;

}
