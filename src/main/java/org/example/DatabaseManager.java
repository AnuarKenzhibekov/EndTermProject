package org.example;

import java.sql.*;

public class DatabaseManager implements AutoCloseable {
    // Database connection
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/EndTermDB";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "0000";

    private static final String INSERT_USER = "INSERT INTO users(username, email) VALUES (?, ?) RETURNING user_id";
    private static final String SELECT_USER = "SELECT * FROM users WHERE username = ? AND email = ?";
    private static final String SELECT_REVIEW = "SELECT feedback, rating FROM theater_reviews WHERE user_id = ?";

    private Connection connection;

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
    }

    // Constructor
    public DatabaseManager() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connect();
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
    }

    // CreateUser constructor
    public User createUser(String UserName, String Email) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) {
            stmt.setString(1, UserName);
            stmt.setString(2, Email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(UserName, Email);
                }
                throw new SQLException("Failed to create employee");
            }
        }
    }

    public User findUser(String UserName, String Email) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER)) {
            stmt.setString(1, UserName);
            stmt.setString(2, Email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("username"), rs.getString("email"));
                } else {
                    throw new SQLException("Failed to find user");
                }
            }
        }
    }


        @Override
        public void close() throws SQLException {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
}



