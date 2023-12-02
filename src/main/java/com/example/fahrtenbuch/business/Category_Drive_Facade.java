package com.example.fahrtenbuch.business;

import com.example.fahrtenbuch.entities.Category_Drive;
import com.example.fahrtenbuch.entities.Drive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category_Drive_Facade {
    private Connection conn;

    public Category_Drive_Facade() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        conn = databaseConnection.getConnection();
    }

    public List<Drive> getDrivesByCategoryId(Integer id) throws SQLException {
        Drive drive = null;
        List<Drive> drives = new ArrayList<>();
        DriveFacade driveFacade = new DriveFacade();
        String query = "SELECT * FROM category_drive WHERE category_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                drive = driveFacade.getDriveById(resultSet.getInt("drive_id"));
                drives.add(drive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drives;
    }

    public void persistCategoryDrive(Category_Drive cd) {
        String query = "INSERT INTO category_drive (category_id, drive_id) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cd.getCategory_id());
            preparedStatement.setInt(2, cd.getDrive_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
