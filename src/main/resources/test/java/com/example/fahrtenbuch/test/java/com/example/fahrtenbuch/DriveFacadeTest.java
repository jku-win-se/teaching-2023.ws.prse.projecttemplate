package com.example.fahrtenbuch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.fahrtenbuch.business.DriveFacade;

public class DriveFacadeTest {

	private DriveFacade driveFacade;
	private FahrtenbucherController fahrController;
	private Connection conn;

	@Before
	public void setUp() {
		driveFacade = new DriveFacade();
		fahrController = new FahrtenbucherController();
		conn = createConnection();
	}

	@After
	public void tearDown() {
		closeConnection(conn);
	}


	private Connection createConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/logbook", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	@Test
	public void testGetCategoryNameByDriveId() {
		int driveId = 1;

		String categoryName = driveFacade.getCategoryNameByDriveId(driveId);
		assertNotNull(categoryName);
	}
	
	@Test
    public void testGetAllCategoryNames() {
        List<String> categoryNames = driveFacade.getAllCategoryNames();

        assertNotNull(categoryNames);
        assertFalse(categoryNames.isEmpty());
        assertTrue(categoryNames.contains("Reparatur"));
    }

	@Test
    public void testGetDriveById() {
        Integer driveIdToTest = 1;

        try {
            Drive drive = driveFacade.getDriveById(driveIdToTest);
            assertNotNull(drive);
            assertEquals(driveIdToTest, drive.getDriveId());
            System.out.println("Drive details: " + drive);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	@Test
    public void testParseTextFieldToIntValidInput() {
        String validInput = "123";
        Integer result = fahrController.parseTextFieldToInt(validInput);
        assertEquals(Integer.valueOf(123), result);
    }

    @Test
    public void testParseTextFieldToIntInvalidInput() {
        String invalidInput = "abc";
        Integer result = fahrController.parseTextFieldToInt(invalidInput);
        assertNull(result);
    }

    @Test
    public void testParseTextFieldToDoubleValidInput() {
        String validInput = "123.45";
        Double result = fahrController.parseTextFieldToDouble(validInput);
        assertEquals(Double.valueOf(123.45), result, 0.001); // specify a delta for double comparison
    }

    @Test
    public void testParseTextFieldToDoubleInvalidInput() {
        String invalidInput = "abc";
        Double result = fahrController.parseTextFieldToDouble(invalidInput);
        assertNull(result);
    }

    
    
    @Test
    public void testGetLicensePlateByDriveId() {
        String licensePlate = driveFacade.getLicense_plateByDriveId(1);
        assertNotNull(licensePlate);
    }
 
}
