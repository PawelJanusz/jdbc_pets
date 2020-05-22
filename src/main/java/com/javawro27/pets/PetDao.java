package com.javawro27.pets;

import com.javawro27.pets.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetDao {

    //Pet DAO reprezentuje DATA ACCESS OBJECT - obiekt dostępu do danych

    private MysqlConnection mysqlConnection;

    public PetDao() {
        this.mysqlConnection = new MysqlConnection();
    }

    public void addToDatabase(Pet pet) {
        try {
            Connection connection = mysqlConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(PetTableQueries.INSERT_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, pet.getName());
            statement.setInt(2, pet.getAge());
            statement.setDouble(3, pet.getWeight());
            statement.setBoolean(4, pet.isPureRace());
            statement.setString(5, pet.getOwnerName());



            int affectedRecords = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> list = new ArrayList<>();

        try(Connection connection = mysqlConnection.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(PetTableQueries.SELECT_ALL_PETS_QUERY)) {
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    Pet newPet = Pet.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .age(resultSet.getInt(3))
                            .weight(resultSet.getDouble(4))
                            .pureRace(resultSet.getBoolean(5))
                            .ownerName(resultSet.getString(6))
                            .build();
                    list.add(newPet);
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public void updatePet(Pet pet){
        if (pet.getId() == null){
            System.err.println("Nie można edytować zwierzaka bez id.");
            return;
        }
        try(Connection connection = mysqlConnection.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(PetTableQueries.UPDATE_PET_QUERY)){
                statement.setString(1, pet.getName());
                statement.setInt(2, pet.getAge());
                statement.setDouble(3, pet.getWeight());
                statement.setBoolean(4, pet.isPureRace());
                statement.setString(5, pet.getOwnerName());
                statement.setLong(6, pet.getId());

                int affectedRecords = statement.executeUpdate();
                System.out.println("Edytowanych rekordów: " + affectedRecords);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePet(Pet pet){
        if (pet.getId() == null){
            System.err.println("Nie można edytować zwierzaka bez id.");
            return;
        }
    }

    public void deletePet(Long petId){
        try(Connection connection = mysqlConnection.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(PetTableQueries.DELETE_PET_QUERY)){
                statement.setLong(1, petId);

                int affectedRecords = statement.executeUpdate();

                System.out.println("Edytowanych rekordów: " + affectedRecords);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
