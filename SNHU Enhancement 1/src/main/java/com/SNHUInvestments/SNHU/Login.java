package com.SNHUInvestments.SNHU;

//Login class for SNHU Investments
//Created by Joseph Caron

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

public class Login {

    // FIXME: add database link once database is created
    // private static final String DB_URL = "database path"

    String greeting = "Hello, please login or register";
    private String username;
    private String passwordHash;
    private String name;
    private String jobTitle;
    Connection connect;

    public boolean login(String username, char[] password) {
        // try to connect to database
        // Comment out try-catch block for testing
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT password_hash FROM users WHERE username = ?";
            PreparedStatement prepState = connect.prepareStatement(sql);
            prepState.setString(1, username);
            ResultSet result = prepState.executeQuery();
            this.connect = connection;

            // if connection is successful, check if hashed passwords match
            if (result.next()) {
                String storedHash = result.getString("password_hash");
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                this.passwordHash = passwordEncoder.encode(new String(password));
                return storedHash.equals(passwordHash);
            }
        }
        catch (SQLException e) {
            System.err.println("Database error during authentication: " + e.getMessage());
        }
        //change to return true for testing
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

    public User register(String username, char[] password, String name, String jobTitle) {
        return new User(username, password, name, jobTitle);
    }
}
