package com.example.fahrtenbuch.business;

import com.example.fahrtenbuch.entities.Category;
import com.example.fahrtenbuch.entities.Category_Drive;
import com.example.fahrtenbuch.entities.Drive;
import com.example.fahrtenbuch.entities.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.sql.Date.*;

public class DatabaseConnection {
    public Connection conn;
    Statement statement = null;

    public Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost/logbook";
        String user = "root";
        String pass = "12345678";

        try {
            conn = DriverManager.getConnection(jdbcURL, user, pass);
            System.out.println("connection successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void initiateDB() throws SQLException {
        //create DB connection
        conn = getConnection();

        //sql statements for drop table
        String dropTableDrive = "DROP TABLE IF EXISTS drive";
        String dropTableVehicle = "DROP TABLE IF EXISTS vehicle";
        String dropTableCategory = "DROP TABLE IF EXISTS category";
        String dropTableCategoryDrive = "DROP TABLE IF EXISTS category_drive";

        // sql statements for create table
        String createVehicle = "CREATE TABLE vehicle (\n" +
                "    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    license_plate VARCHAR(255) NOT NULL,\n" +
                "    odometer DOUBLE NOT NULL\n" +
                ");";

        String createDrive = "CREATE TABLE drive (\n" +
                "    drive_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    vehicle_id INT NOT NULL,\n" +
                "    drive_date DATE NOT NULL,\n" +
                "    departure_time TIME,\n" +
                "    arrival_time TIME,\n" +
                "    waiting_time INT,\n" + //in minutes
                "    driven_kilometres DOUBLE,\n" +
                "    status VARCHAR(255),\n" +
                "    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)\n" +
                ");";

        String createCategory = "CREATE TABLE category (\n" +
                "    category_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    category_name VARCHAR(255) NOT NULL\n" +
                ");";

        String createCategoryDrive = "CREATE TABLE category_drive (\n" +
                "    category_id INT,\n" +
                "    drive_id INT,\n" +
                "    PRIMARY KEY (category_id, drive_id),\n" +
                "    FOREIGN KEY (category_id) REFERENCES Category(category_id),\n" +
                "    FOREIGN KEY (drive_id) REFERENCES Drive(drive_id)\n" +
                ");";

        try {
            statement = conn.createStatement();

            statement.executeUpdate(dropTableCategoryDrive);
            statement.executeUpdate(dropTableDrive);
            statement.executeUpdate(dropTableVehicle);
            statement.executeUpdate(dropTableCategory);

            statement.executeUpdate(createVehicle);
            statement.executeUpdate(createDrive);
            statement.executeUpdate(createCategory);
            statement.executeUpdate(createCategoryDrive);

            VehicleFacade vehicleFacade = new VehicleFacade();
            vehicleFacade.persistVehicle(new Vehicle("testlicense", 123456.0));
            vehicleFacade.persistVehicle(new Vehicle("secondlicense", 100.0));
            //vehicleFacade.deleteVehicleById(2);

            DriveFacade driveFacade = new DriveFacade();
            driveFacade.persistDrive(new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), 10, 100.0));
            driveFacade.persistDrive(new Drive(2, Date.valueOf(LocalDate.now())));
            driveFacade.persistDrive(new Drive(2, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now())));

            CategoryFacade categoryFacade = new CategoryFacade();
            categoryFacade.persistCategory(new Category("Reparatur"));
            categoryFacade.persistCategory(new Category("Freizeit"));

            Category_Drive_Facade categoryDriveFacade = new Category_Drive_Facade();
            categoryDriveFacade.persistCategoryDrive(new Category_Drive(1,1));
            categoryDriveFacade.persistCategoryDrive(new Category_Drive(1,2));
            categoryDriveFacade.persistCategoryDrive(new Category_Drive(2,2));
            categoryDriveFacade.persistCategoryDrive(new Category_Drive(2,3));

            System.out.println(categoryDriveFacade.getDrivesByCategoryId(1));
            System.out.println(categoryDriveFacade.getDrivesByCategoryId(2));

            System.out.println(vehicleFacade.getAllVehicles());
            System.out.println(driveFacade.getAllDrives());
            System.out.println(categoryFacade.getAllCategories());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
