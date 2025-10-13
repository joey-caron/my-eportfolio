package com.SNHUInvestments.SNHU;

//Login class for SNHU Investments
//Created by Joseph Caron

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

public class Login {


    static final String DB_URL = "jdbc:sqlite:database";

    Connection connect;

    public boolean login(String username, char[] password) {
        // Try to connect to database
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT password FROM users WHERE username = ?";
            PreparedStatement prepState = connection.prepareStatement(sql);
            prepState.setString(1, username);
            ResultSet result = prepState.executeQuery();
            this.connect = connection;

            // If connection is successful, check if hashed passwords match
            if (result.next()) {
                String storedHash = result.getString("password");
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                return passwordEncoder.matches(new String(password), storedHash);
            }
        }
        catch (SQLException e) {
            System.err.println("Database error during authentication: " + e.getMessage());
        }
        return false;
    }

    public void logout() {
        if (connect != null) { // Disconnects from the database
            try {
                connect.close();
                System.out.println("Database connection closed.");
            }
            catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
            finally {
                this.connect = null; //Set connection to null
            }
        }
        System.out.println("User logged out successfully.");
    }

}
