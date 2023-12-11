

import com.example.fahrtenbuch.business.DatabaseConnection;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBackup {
    private DatabaseConnection dataBaseConnection;
    private Connection conn;

    public TestBackup() {
        dataBaseConnection = new DatabaseConnection();
        conn = dataBaseConnection.getConnection();
    }

    @Test
    public void testExportData() {

        // test Export
        dataBaseConnection.exportDataToCSV();
        File vehicleFile = new File("vehicle.csv");
        File categoryFile = new File("category.csv");
        File driveFile = new File("drive.csv");
        File categoryDriveFile = new File("category_drive.csv");

        assertTrue(vehicleFile.exists());
        assertTrue(categoryFile.exists());
        assertTrue(driveFile.exists());
        assertTrue(categoryDriveFile.exists());
    }

    @Test
    public void testImportData() {
        // delete all existing backup files
        File vehicleFile = new File("vehicle.csv");
        File categoryFile = new File("category.csv");
        File driveFile = new File("drive.csv");
        File categoryDriveFile = new File("category_drive.csv");

        if (vehicleFile.exists())
            vehicleFile.delete();
        if (categoryFile.exists())
            categoryFile.delete();
        if (driveFile.exists())
            driveFile.delete();
        if (categoryDriveFile.exists())
            categoryDriveFile.delete();

        // export data
        try {
            FileWriter writer = new FileWriter("vehicle.csv");
            writer.write("vehicle_id;license_plate;odometer\n");
            writer.write("1;LL-123DG;150.5\n");
            writer.write("2;L-145KT;200.0\n");
            writer.write("3;WL-246DT;180.3\n");
            writer.close();

            writer = new FileWriter("drive.csv");
            writer.write("drive_id;vehicle_id;drive_date;departure_time;arrival_time;waiting_time;driven_kilometres;status\n");
            writer.write("1;1;2023-01-01;09:00:00;11:30:00;30;50.5;ACTIVE\n");
            writer.write("2;2;2023-01-02;08:45:00;10:15:00;15;30.2;COMPLETE\n");
            writer.write("3;3;2023-01-03;10:00:00;12:00:00;0;40.0;ACTIVE\n");
            writer.close();

            writer = new FileWriter("category.csv");
            writer.write("category_id;category_name\n");
            writer.write("1;Transport\n");
            writer.write("2;Termin\n");
            writer.write("3;Andere\n");
            writer.close();

            writer = new FileWriter("category_drive.csv");
            writer.write("category_id;drive_id\n");
            writer.write("2;2\n");
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        // import data
        dataBaseConnection.importDataFromCSV();

        // count lines in database and check if lines are correct
        assertEquals(3, countTableRows("vehicle"));
        assertEquals(3, countTableRows("drive"));
        assertEquals(3, countTableRows("category"));
        assertEquals(1, countTableRows("category_drive"));
    }

    private int countTableRows(String tableName) {
        int count = 0;
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM " + tableName);
            var resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}


