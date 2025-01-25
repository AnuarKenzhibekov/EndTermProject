package org.example;

import java.sql.*;

public class DatabaseManager implements AutoCloseable {
    // Database connection
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/EndTermDB";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "0000";

    private static final String INSERT_USER = "INSERT INTO users(username, email) VALUES (?, ?) RETURNING user_id";
    private static final String SELECT_USER = "SELECT * FROM users WHERE username = ? AND email = ?";
    private static final String SELECT_REVIEWS = "SELECT u.userName, tr.feedback, tr.grade " + "FROM users u " + "JOIN theater_reviews tr ON u.user_id = tr.user_id " + "WHERE u.user_id = ?";;

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
    public User createUser(User user) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setUserID(rs.getInt("user_id"));
                    return new User(user.getUserName(), user.getEmail());
                }
                throw new SQLException("Failed to create employee");
            }
        }
    }

    public User findUser(User user) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setUserID(rs.getInt("user_id"));
                    return new User(rs.getString("username"), rs.getString("email"));
                } else {
                    throw new SQLException("Failed to find user");
                }
            }
        }
    }

    public Reviews getReviewByUserId(int userId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_REVIEWS)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String feedback = rs.getString("feedback");
                    int grade = rs.getInt("grade");

                    return new Reviews(userName, feedback, grade);
                } else {
                    return null;
                }
            }
        }
    }


        @Override
        public void close () throws SQLException {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }



