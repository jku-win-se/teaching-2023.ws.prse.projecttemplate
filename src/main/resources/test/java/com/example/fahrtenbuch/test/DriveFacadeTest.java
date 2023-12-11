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

public class DriveFacadeTest {
    private DatabaseConnection dataBaseConnection;
    private Connection conn;

    public DriveFacadeTest() {
        dataBaseConnection = new DatabaseConnection();
        conn = dataBaseConnection.getConnection();
    }

    @Test
    public void testGetDrivesByLicensePlate() {
        String licensePlate = "testlicense";

        try {
            List<Drive> drives = driveFacade.getDrivesByLicensePlate(licensePlate);

            assertTrue(drives != null && !drives.isEmpty());

            for (Drive drive : drives) {
                assertEquals(licensePlate, drive.getLicensePlate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    void testPersistRecurringDrive() {
        String licensePlate = "ABC123";
        Date startDate = Date.valueOf("2023-01-01");
        Date endDate = Date.valueOf("2023-01-10");
        int interval = 2;

        boolean result = driveFacade.persistRecurringDrive(licensePlate, startDate, endDate, interval);

        assertTrue(result, "persistRecurringDrive sollte true zurückgeben");

    }
}

