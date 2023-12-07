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
}

