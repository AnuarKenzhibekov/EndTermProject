package Repositories;

import MainPackage.Seats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsRepository {
    private final Connection connection;

    public SeatsRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Seats> getAllAvailableSeats() throws SQLException {
        String sql = "SELECT * FROM seats WHERE status = 'available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<Seats> availableSeats = new ArrayList<>();

            while (rs.next()) {
                availableSeats.add(new Seats(
                        rs.getInt("seat_id"),
                        rs.getInt("showtime_id"),
                        rs.getInt("hall_id"),
                        rs.getInt("number"),
                        rs.getString("row"),
                        rs.getString("status"),
                        rs.getInt("movie_id")
                ));
            }
            return availableSeats;
        }
    }



    public void bookSeat(int seatId) throws SQLException {
        String sql = "UPDATE seats SET status = 'booked' WHERE seat_id = ? AND status = 'available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, seatId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Seat " + seatId + " has been successfully booked.");
            } else {
                System.out.println("The seat is either already booked or doesn't exist.");
            }
        }
    }
}

