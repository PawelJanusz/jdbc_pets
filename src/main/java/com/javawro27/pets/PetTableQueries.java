package com.javawro27.pets;

public interface PetTableQueries {

        String CREATE_DATABASE_QUERY =
                "CREATE DATABASE IF NOT EXISTS `jwro27_pets_jdbc`";

        String CREATE_TABLE_QUERY =
                "CREATE TABLE IF NOT EXISTS `pets`(\n" +
                "`id` INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "`name` VARCHAR(15) NOT NULL,\n" +
                "`age` INT NOT NULL,\n" +
                "`weight` DECIMAL(10,3),\n" +
                "`pure_race` BOOLEAN NOT NULL,\n" +
                "`owner_name` VARCHAR(15) NOT NULL\n" +
                ");";

        String INSERT_STUDENT_QUERY =
                "INSERT INTO `pets` (`name`, `age`, `weight`, `pure_race`, `owner_name`) VALUES (?, ?, ?, ?, ?);";

        String SELECT_ALL_PETS_QUERY =
                "SELECT * FROM pets;";

        String UPDATE_PET_QUERY =
                "UPDATE pets SET " +
                        "name = ?, " +
                        "age = ?, " +
                        "weight = ?, " +
                        "pure_race = ?, " +
                        "owner_name = ? " +
                        "WHERE id = ?;";

        String DELETE_PET_QUERY =
                "DELETE FROM pets WHERE id = ?;";

}
