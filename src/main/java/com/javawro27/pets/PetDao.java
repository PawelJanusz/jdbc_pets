package com.javawro27.pets;

import com.javawro27.pets.model.Pet;

import java.sql.*;
import java.util.ArrayList;
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
            PreparedStatement statement = connection.prepareStatement(PetTableQueries.INSERT_PET_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, pet.getName());
            statement.setString(2, pet.getPetType());
            statement.setInt(3, pet.getAge());
            statement.setDouble(4, pet.getWeight());
            statement.setBoolean(5, pet.isPureRace());
            statement.setString(6, pet.getOwnerName());

            int affectedRecords = statement.executeUpdate();
            System.out.println("Dodane rekordy: " + affectedRecords);

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
                            .petType(resultSet.getString(3))
                            .age(resultSet.getInt(4))
                            .weight(resultSet.getDouble(5))
                            .pureRace(resultSet.getBoolean(6))
                            .ownerName(resultSet.getString(7))
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
                statement.setString(2, pet.getPetType());
                statement.setInt(3, pet.getAge());
                statement.setDouble(4, pet.getWeight());
                statement.setBoolean(5, pet.isPureRace());
                statement.setString(6, pet.getOwnerName());
                statement.setLong(7, pet.getId());

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
