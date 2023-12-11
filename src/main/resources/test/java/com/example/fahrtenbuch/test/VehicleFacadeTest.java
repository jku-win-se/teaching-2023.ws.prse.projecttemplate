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

public class VehicleFacadeTest {
    private DatabaseConnection dataBaseConnection;
    private Connection conn;

    public DriveFacadeTest() {
        dataBaseConnection = new DatabaseConnection();
        conn = dataBaseConnection.getConnection();
    }

    @Test
    public void testPersistAndGetVehicle() throws SQLException {

        Vehicle testVehicle = new Vehicle("TEST123", 10000.0);
        vehicleFacade.persistVehicle(testVehicle);

        assertNotNull(testVehicle.getVehicleId());

        Vehicle retrievedVehicle = vehicleFacade.getVehicleById(testVehicle.getVehicleId());
        assertNotNull(retrievedVehicle);
        assertEquals(testVehicle.getLicensePlate(), retrievedVehicle.getLicensePlate());
        assertEquals(testVehicle.getOdometer(), retrievedVehicle.getOdometer(), 0.01);
    }

    @Test
    public void testGetAllVehicles() {

        List<Vehicle> initialVehicles = vehicleFacade.getAllVehicles();

        Vehicle testVehicle = new Vehicle("TEST456", 20000.0);
        vehicleFacade.persistVehicle(testVehicle);

        List<Vehicle> updatedVehicles = vehicleFacade.getAllVehicles();
        assertEquals(initialVehicles.size() + 1, updatedVehicles.size());
    }
}


}