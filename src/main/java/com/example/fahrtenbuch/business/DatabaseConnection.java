package com.example.fahrtenbuch.business;

import com.example.fahrtenbuch.entities.Category;
import com.example.fahrtenbuch.entities.CategoryDrive;
import com.example.fahrtenbuch.entities.Drive;
import com.example.fahrtenbuch.entities.Vehicle;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DatabaseConnection {
    public Connection conn;
    Statement statement = null;

    public Connection getConnection() {
        String jdbcURL = "jdbc:mysql://logbook-do-user-15383945-0.c.db.ondigitalocean.com:25060/logbook";
        String user = "logbook";
        String pass = "AVNS_3f_CWsrS8a_9lHfghe1";

        try {
            conn = DriverManager.getConnection(jdbcURL, user, pass);
            //System.out.println("connection successfully");
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
                "    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id)\n" +
                ");";

        String createCategory = "CREATE TABLE category (\n" +
                "    category_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    category_name VARCHAR(255) NOT NULL\n" +
                ");";

        String createCategoryDrive = "CREATE TABLE category_drive (\n" +
                "    category_id INT,\n" +
                "    drive_id INT,\n" +
                "    PRIMARY KEY (category_id, drive_id),\n" +
                "    FOREIGN KEY (category_id) REFERENCES category(category_id),\n" +
                "    FOREIGN KEY (drive_id) REFERENCES drive(drive_id)\n" +
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

            CategoryDriveFacade categoryDriveFacade = new CategoryDriveFacade();
            categoryDriveFacade.persistCategoryDrive(new CategoryDrive(1, 1));
            categoryDriveFacade.persistCategoryDrive(new CategoryDrive(1, 2));
            categoryDriveFacade.persistCategoryDrive(new CategoryDrive(2, 2));
            categoryDriveFacade.persistCategoryDrive(new CategoryDrive(2, 3));

            //System.out.println(categoryDriveFacade.getDrivesByCategoryId(1));
            //System.out.println(categoryDriveFacade.getDrivesByCategoryId(2));

            List<Drive> drives = driveFacade.getDrivesByLicensePlate("testlicense");

            /*for (Drive drive : drives) {
                System.out.println(drive);
            }

            System.out.println(vehicleFacade.getAllVehicles());
            System.out.println(driveFacade.getAllDrives());
            System.out.println(categoryFacade.getAllCategories());*/
            exportDataToCSV();
            importDataFromCSV();
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

    public void exportDataToCSV() {
        try {
            // export table vehicle
            exportTableToCSV("vehicle", "vehicle.csv");
            exportTableToCSV("category", "category.csv");
            exportTableToCSV("drive", "drive.csv");
            exportTableToCSV("category_drive", "category_drive.csv");

            //System.out.println("Daten wurden erfolgreich exportiert.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void exportTableToCSV(String tableName, String filePath)
            throws SQLException, IOException {
        String sql = "SELECT * FROM " + tableName;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
             FileWriter writer = new FileWriter(filePath)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // header line
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnName(i));
                if (i < columnCount) {
                    writer.write(";");
                }
            }
            writer.write("\n");

            // data lines
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = resultSet.getString(i);
                    if (resultSet.wasNull()) {
                        writer.write("NULL");
                    } else {
                        writer.write(value);
                    }
                    if (i < columnCount) {
                        writer.write(";");
                    }
                }
                writer.write("\n");
            }
        }
    }

    public void importDataFromCSV() {
        //sql statements for drop table
        String deleteTableDrive = "DELETE FROM drive";
        String deleteTableVehicle = "DELETE FROM vehicle";
        String deleteTableCategory = "DELETE FROM category";
        String deleteTableCategoryDrive = "DELETE FROM category_drive";

        //drop existing tables
        try {
            statement = conn.createStatement();
            statement.executeUpdate(deleteTableCategoryDrive);
            statement.executeUpdate(deleteTableDrive);
            statement.executeUpdate(deleteTableVehicle);
            statement.executeUpdate(deleteTableCategory);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //check if files exist and read data
        File vehicleFile = new File("vehicle.csv");
        File categoryFile = new File("category.csv");
        File driveFile = new File("drive.csv");
        File categoryDriveFile = new File("category_drive.csv");

        if(vehicleFile.exists() && categoryDriveFile.exists() && driveFile.exists() && categoryDriveFile.exists()) {
            readCSV("vehicle", "vehicle.csv");
            readCSV("category", "category.csv");
            readCSV("drive", "drive.csv");
            readCSV("category_drive", "category_drive.csv");
        }
    }

    public void readCSV(String tableName, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (skipFirstLine) { // skip the header line
                    skipFirstLine = false;
                    continue;
                }
                String[] data = line.split(";");
                insertRow(tableName, data);
            }
            //System.out.println("Daten wurden erfolgreich importiert.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertRow(String tableName, String[] data) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(tableName).append(" VALUES (");
        for (int i = 0; i < data.length; i++) {
            query.append("?");
            if (i < data.length - 1) {
                query.append(",");
            }
        }
        query.append(")");

        try (PreparedStatement preparedStatement = conn.prepareStatement(query.toString())) {
            for (int i = 0; i < data.length; i++) {
                if (data[i].equals("NULL")) {
                    preparedStatement.setNull(i + 1, Types.NULL);
                } else {
                    preparedStatement.setString(i + 1, data[i]);
                }
            }
            preparedStatement.executeUpdate();
        }
    }


}
