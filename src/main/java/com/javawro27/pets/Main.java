package com.javawro27.pets;

import com.javawro27.pets.model.Pet;

import java.util.Random;

public class Main {


    public static void main(String[] args) {

        PetDao dao = new PetDao();
        Random random = new Random();
        int age = random.nextInt(15);

        // Add pet
        Pet pet1 = Pet.builder()
                .name("Azor")
                .age(age)
                .weight(8.5)
                .pureRace(false)
                .ownerName("Piotr")
                .build();
        Pet pet2 = Pet.builder()
                .name("Bobek")
                .age(age)
                .weight(4.23)
                .pureRace(false)
                .ownerName("Andrzej")
                .build();

        dao.addToDatabase(pet1);
        dao.addToDatabase(pet2);
        dao.addToDatabase(pet2);
        dao.addToDatabase(pet1);

    }
}
