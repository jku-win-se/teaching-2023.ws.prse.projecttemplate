package com.example.fahrtenbuch.business;
import com.example.fahrtenbuch.entities.Drive;
import com.example.fahrtenbuch.entities.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DriveFacade {
    private Connection conn;
    public DriveFacade() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        conn = databaseConnection.getConnection();
    }
    public Drive getDriveById(Integer id) throws SQLException {
        Drive drive = null;
        String query = "SELECT * FROM drive WHERE drive_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                drive = new Drive();
                drive.setDriveId(resultSet.getInt("drive_id"));
                drive.setVehicleId(resultSet.getInt("vehicle_id"));
                drive.setDate(resultSet.getDate("drive_date"));
                drive.setDepartureTime(resultSet.getTime("departure_time"));
                drive.setArrivalTime(resultSet.getTime("arrival_time"));
                drive.setWaitingTime(resultSet.getInt("waiting_time"));
                drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                drive.setStatus(Status.valueOf(resultSet.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drive;
    }

    public List<Drive> getDrivesByLicensePlate(String licensePlate) throws SQLException {
        List<Drive> drives = new ArrayList<>();
        String query = "SELECT * FROM drive " +
                "JOIN vehicle ON drive.vehicle_id = vehicle.vehicle_id " +
                "WHERE vehicle.license_plate = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, licensePlate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Drive drive = new Drive();
                drive.setDriveId(resultSet.getInt("drive_id"));
                drive.setVehicleId(resultSet.getInt("vehicle_id"));
                drive.setDate(resultSet.getDate("drive_date"));
                drive.setDepartureTime(resultSet.getTime("departure_time"));
                drive.setArrivalTime(resultSet.getTime("arrival_time"));
                drive.setWaitingTime(resultSet.getInt("waiting_time"));
                drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                drive.setStatus(Status.valueOf(resultSet.getString("status")));
                drives.add(drive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drives;
    }

    public List<Drive> getAllDrives() {
        List<Drive> drives = new ArrayList<>();
        String query = "SELECT * FROM drive";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Drive drive = new Drive();
                drive.setDriveId(resultSet.getInt("drive_id"));
                drive.setVehicleId(resultSet.getInt("vehicle_id"));
                drive.setDate(resultSet.getDate("drive_date"));
                drive.setDepartureTime(resultSet.getTime("departure_time"));
                drive.setArrivalTime(resultSet.getTime("arrival_time"));
                drive.setWaitingTime(resultSet.getInt("waiting_time"));
                drive.setDrivenKilometres(resultSet.getDouble("driven_kilometres"));
                drive.setStatus(Status.valueOf(resultSet.getString("status")));
                drives.add(drive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drives;
    }
    public void persistDrive(Drive v) {
        //check if departure time is before arrival time
        if(v.getDepartureTime() != null && v.getArrivalTime() != null && v.getDepartureTime().after(v.getArrivalTime()))
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }

        String query = "INSERT INTO drive (vehicle_id, drive_date, departure_time, arrival_time, waiting_time, driven_kilometres, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,v.getVehicleId());
            preparedStatement.setDate(2,v.getDate());
            preparedStatement.setTime(3,v.getDepartureTime());
            preparedStatement.setTime(4,v.getArrivalTime());
            preparedStatement.setInt(5,v.getWaitingTime());
            preparedStatement.setDouble(6,v.getDrivenKilometres());
            preparedStatement.setString(7,v.getStatus().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void persistRecurringDrive(Integer vehicleId, Date startDate, Date endDate, int interval) {
        if (vehicleId == null || startDate == null || endDate == null || interval <= 0) {
            return;
        }

        try {
            persistDrive(new Drive(vehicleId, startDate));

            LocalDate currentDate = startDate.toLocalDate();
            LocalDate endLocalDate = endDate.toLocalDate();

            while (currentDate.isBefore(endLocalDate)) {
                currentDate = currentDate.plusDays(interval);
                if (currentDate.isBefore(endLocalDate) || currentDate.isEqual(endLocalDate)) {
                    persistDrive(new Drive(vehicleId, Date.valueOf(currentDate)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDriveById(Integer id) {
        String query = "DELETE FROM drive WHERE drive_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getLastDriveId() {
        Integer lastDriveId = null;
        String query = "SELECT drive_id FROM drive ORDER BY drive_id DESC LIMIT 1";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastDriveId = resultSet.getInt("drive_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastDriveId;
    }
}
