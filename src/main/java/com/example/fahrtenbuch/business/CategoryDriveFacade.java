//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.fahrtenbuch.business;

import com.example.fahrtenbuch.entities.Category_Drive;
import com.example.fahrtenbuch.entities.Drive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDriveFacade {
    private Connection conn;

    public CategoryDriveFacade() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        this.conn = databaseConnection.getConnection();
    }

    public List<Drive> getDrivesByCategoryId(Integer id) throws SQLException {
        Drive drive = null;
        List<Drive> drives = new ArrayList();
        DriveFacade driveFacade = new DriveFacade();
        String query = "SELECT * FROM category_drive WHERE category_id = ?";

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                drive = driveFacade.getDriveById(resultSet.getInt("drive_id"));
                drives.add(drive);
            }
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        return drives;
    }

    public void persistCategoryDrive(Category_Drive cd) {
        String query = "INSERT INTO category_drive (category_id, drive_id) VALUES (?, ?)";

        try {
            Integer driveId = cd.getDrive_id();
            if (driveId == null) {
                throw new IllegalArgumentException("drive_id darf nicht null sein.");
            }

            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, cd.getCategory_id());
            preparedStatement.setInt(2, cd.getDrive_id());
            preparedStatement.executeUpdate();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }
}
