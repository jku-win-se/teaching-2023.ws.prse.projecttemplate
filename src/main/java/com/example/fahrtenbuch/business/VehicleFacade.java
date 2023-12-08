package com.example.fahrtenbuch.business;
import com.example.fahrtenbuch.entities.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleFacade {
    private Connection conn;
    public VehicleFacade() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        conn = databaseConnection.getConnection();
    }

    public Vehicle getVehicleById(Integer id) throws SQLException {
        Vehicle vehicle = null;
        String query = "SELECT * FROM vehicle WHERE vehicle_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(resultSet.getInt("vehicle_id"));
                vehicle.setLicensePlate(resultSet.getString("license_plate"));
                vehicle.setOdometer(resultSet.getDouble("odometer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicle";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(resultSet.getInt("vehicle_id"));
                vehicle.setLicensePlate(resultSet.getString("license_plate"));
                vehicle.setOdometer(resultSet.getDouble("odometer"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public void persistVehicle(Vehicle v) {
        String query = "INSERT INTO vehicle (license_plate, odometer) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, v.getLicensePlate());
            preparedStatement.setDouble(2, v.getOdometer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteVehicleById(Integer id) {
        String query = "DELETE FROM vehicle WHERE vehicle_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        Vehicle vehicle = null;
        String query = "SELECT * FROM vehicle WHERE license_plate = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, licensePlate);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(resultSet.getInt("vehicle_id"));
                vehicle.setLicensePlate(resultSet.getString("license_plate"));
                vehicle.setOdometer(resultSet.getDouble("odometer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    public Integer getVehicleIdByLicensePlate(String licensePlate) {
        Integer vehicleId = null;
        String query = "SELECT vehicle_id FROM vehicle WHERE license_plate = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, licensePlate);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vehicleId = resultSet.getInt("vehicle_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleId;
    }
}
