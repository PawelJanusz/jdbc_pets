package com.javawro27.pets;

import com.javawro27.pets.model.Pet;

import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        PetDao dao = new PetDao();

        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.println("Podaj komendę [add/list/delete/update/quit]");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("add")){
                addPets(dao, scanner);
            }else if (command.equalsIgnoreCase("list")){
                listPets(dao);
            }else if (command.equalsIgnoreCase("delete")){
                deletePet(dao, scanner);
            }else if (command.equalsIgnoreCase("update")){
                updatePet(dao, scanner);
            }

        }while (!command.equalsIgnoreCase("quit"));


    }

    private static void addPets(PetDao dao, Scanner scanner){
        System.out.println("Podaj parametry: IMIE GATUNEK WIEK WAGA CZY_RASA_CZYSTA IMIE_WLASCICIELA");
        String line = scanner.nextLine();
        String[] words = line.split(" ");

        Pet pet = Pet.builder()
                .name(words[0])
                .petType(words[1])
                .age(Integer.parseInt(words[2]))
                .weight(Double.parseDouble(words[3]))
                .pureRace(Boolean.parseBoolean(words[4]))
                .ownerName(words[5])
                .build();
        dao.addToDatabase(pet);
    }

    private static void listPets(PetDao dao){
        System.out.println("Lista zwierząt domowych: ");
        dao.getAllPets().forEach(System.out::println);
    }

    private static void deletePet(PetDao dao, Scanner scanner){
        System.out.println("Podaj ID zwierzaka: ");
        Long id = Long.parseLong(scanner.nextLine());
        dao.deletePet(id);
    }

    private static void updatePet(PetDao dao, Scanner scanner){
        System.out.println("Podaj ID zwierzaka: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.println("Podaj parametry: IMIE GATUNEK WIEK WAGA CZY_RASA_CZYSTA IMIE_WLASCICIELA");
        String line = scanner.nextLine();
        String[] words = line.split(" ");
        Pet pet = Pet.builder()
                .name(words[0])
                .petType(words[1])
                .age(Integer.parseInt(words[2]))
                .weight(Double.parseDouble(words[3]))
                .pureRace(Boolean.parseBoolean(words[4]))
                .ownerName(words[5])
                .id(id)
                .build();
        dao.updatePet(pet);
    }






    //        PetDao dao = new PetDao();
//        Random random = new Random();
//        int age = random.nextInt(15);
//
//        // Add pet
//        Pet pet1 = Pet.builder()
//                .name("Azor")
//                .age(age)
//                .weight(8.5)
//                .pureRace(false)
//                .ownerName("Piotr")
//                .build();
//        Pet pet2 = Pet.builder()
//                .name("Bobek")
//                .age(age)
//                .weight(4.23)
//                .pureRace(false)
//                .ownerName("Andrzej")
//                .build();
//
//        dao.addToDatabase(pet1);
//        dao.addToDatabase(pet2);
//        dao.addToDatabase(pet2);
//        dao.addToDatabase(pet1);
}
