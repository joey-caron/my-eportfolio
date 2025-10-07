package com.SNHUInvestments.SNHU;

// CRUD class for SNHU Investments
//Created by Joseph Caron

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    //Create User
    public void addUser(User user) {

        String sql = "INSERT INTO users (username, password, name, job_title) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getName());
            statement.setString(4, user.getJobTitle());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Create Customer
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (custId, first_name, last_name, address, " +
                "phone, email, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4,customer.getAddress());
            statement.setString(5, customer.getPhone());
            statement.setString(6, customer.getEmail());
            statement.setString(7, customer.getStatus());
            statement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                Customer customer = new Customer(
                        results.getString("custId"),
                        results.getString("first_name"),
                        results.getString("last_name"),
                        results.getString("address"),
                        results.getString("phone"),
                        results.getString("email"),
                        results.getString("status")
                );
                customers.add(customer);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Search users by username
    public boolean searchUserByUsername(String username) {
        String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE username = ?)";
        boolean exists = false;
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    exists = results.getInt(1) == 1;
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Search customer by ID boolean
    public boolean searchCustById(String custId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM customers WHERE custId = ?)";
        boolean exists = false;
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, custId);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    exists = results.getInt(1) == 1;
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Search by id
    public Customer getCustomerById(String custId) {
        String sql = "SELECT * FROM customers WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, custId);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return new Customer(
                            results.getString("custId"),
                            results.getString("first_name"),
                            results.getString("last_name"),
                            results.getString("address"),
                            results.getString("phone"),
                            results.getString("email"),
                            results.getString("status")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search by first name
    public Customer getCustomerByFirst(String firstName) {
        String sql = "SELECT * FROM customers WHERE first_name LIKE %?%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return new Customer(
                            results.getString("custId"),
                            results.getString("first_name"),
                            results.getString("last_name"),
                            results.getString("address"),
                            results.getString("phone"),
                            results.getString("email"),
                            results.getString("status")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search by last name
    public Customer getCustomerByLast(String lastName) {
        String sql = "SELECT * FROM customers WHERE last_name LIKE %?%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lastName);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return new Customer(
                            results.getString("custId"),
                            results.getString("first_name"),
                            results.getString("last_name"),
                            results.getString("address"),
                            results.getString("phone"),
                            results.getString("email"),
                            results.getString("status")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search by address
    public Customer getCustomerByAddress(String address) {
        String sql = "SELECT * FROM customers WHERE address LIKE %?%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, address);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return new Customer(
                            results.getString("custId"),
                            results.getString("first_name"),
                            results.getString("last_name"),
                            results.getString("address"),
                            results.getString("phone"),
                            results.getString("email"),
                            results.getString("status")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search by phone
    public Customer getCustomerByPhone(String phone) {
        String sql = "SELECT * FROM customers WHERE phone LIKE %?%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return new Customer(
                            results.getString("custId"),
                            results.getString("first_name"),
                            results.getString("last_name"),
                            results.getString("address"),
                            results.getString("phone"),
                            results.getString("email"),
                            results.getString("status")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Search by email
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email LIKE %?%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return new Customer(
                            results.getString("custId"),
                            results.getString("first_name"),
                            results.getString("last_name"),
                            results.getString("address"),
                            results.getString("phone"),
                            results.getString("email"),
                            results.getString("status")
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Edit first name
    public void editCustomerFirst(String firstName, String id) {
        String sql = "UPDATE customers SET first_name = ? WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit last name
    public void editCustomerLast(String lastName, String id) {
        String sql = "UPDATE customers SET last_name = ? WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lastName);
            statement.setString(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit address
    public void editCustomerAddress(String address, String id) {
        String sql = "UPDATE customers SET address = ? WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, address);
            statement.setString(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit phone
    public void editCustomerPhone(String phone, String id) {
        String sql = "UPDATE customers SET phone = ? WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone);
            statement.setString(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit email
    public void editCustomerEmail(String email, String id) {
        String sql = "UPDATE customers SET email = ? WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit status
    public void editCustomerStatus(String status, String id) {
        String sql = "UPDATE customers SET status = ? WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setString(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete customer
    public void deleteCustomer(String custId) {
        String sql = "DELETE FROM customers WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, custId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
