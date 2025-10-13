package com.SNHUInvestments.SNHU;

// CRUD class for SNHU Investments
//Created by Joseph Caron

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.SNHUInvestments.SNHU.Login.DB_URL;

public class CRUD {

    // Initialize exception logging
    private static final Logger logger = LoggerFactory.getLogger(CRUD.class);

    // Initialize search variables
    List<Customer> customers = new ArrayList<>();
    Customer customer;

    // Create User
    public void addUser(User user) {

        String sql = "INSERT INTO users (username, password, name, job_title) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getName());
            statement.setString(4, user.getJobTitle());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("An error occurred while trying to add user: {}", e.getMessage(), e);
        }

    }

    // Create Customer
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (custId, first_name, last_name, address, " +
                "phone, email, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            boolean validPhone = Validator.numberValidator(customer.getPhone(), 1000000000, 9999999999L);
            if (!validPhone) {
                System.out.println("Not a valid phone number");
                throw new IllegalArgumentException("Not a valid number");
            }
            else {
                statement.setString(1, customer.getCustomerId());
                statement.setString(2, customer.getFirstName());
                statement.setString(3, customer.getLastName());
                statement.setString(4, customer.getAddress());
                statement.setString(5, customer.getPhone());
                statement.setString(6, customer.getEmail());
                statement.setString(7, customer.getStatus());
                statement.executeUpdate();
            }
        }
        catch (SQLException e) {
            logger.error("An error occurred while trying to add customer: {}", e.getMessage(), e);
        }
    }

    // Read all
    public void getAllCustomers() {
        String sql = "SELECT * FROM customers";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                customer = new Customer(
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
            logger.error("An error occurred while trying to retrieve customer list: {}", e.getMessage(), e);
        }
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        customers.clear();

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
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        return exists;
    }

    // Search customer by ID boolean
    public boolean searchCustById(String custId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM customers WHERE custId = ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, custId);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                return results.getInt(1) == 1;

            }

        }
        catch (SQLException e) {
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        return false;
    }

    // Search by id
    public void getCustomerById(String custId) {
        String sql = "SELECT * FROM customers WHERE custId LIKE ?";
        custId = "%" + custId + "%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, custId);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    customer = new Customer(
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
        }
        catch (SQLException e) {
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        if (customers.isEmpty()) {
            System.out.println("No customer matches search parameters");
        }
        else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }

        }
        customers.clear();
    }

    // Search by first name
    public void getCustomerByFirst(String firstName) {
        String sql = "SELECT * FROM customers WHERE first_name LIKE ?";
        firstName = "%" + firstName + "%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    customer = new Customer(
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
        }
        catch (SQLException e) {
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        if (customers.isEmpty()) {
            System.out.println("No customer matches search parameters");
        }
        else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }

        }
        customers.clear();
    }

    // Search by last name
    public void getCustomerByLast(String lastName) {
        String sql = "SELECT * FROM customers WHERE last_name LIKE ?";
        lastName= "%" + lastName + "%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lastName);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    customer = new Customer(
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
        }
        catch (SQLException e) {
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        if (customers.isEmpty()) {
            System.out.println("No customer matches search parameters");
        }
        else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }

        }
        customers.clear();
    }

    // Search by address
    public void getCustomerByAddress(String address) {
        String sql = "SELECT * FROM customers WHERE address LIKE ?";
        address = "%" + address + "%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, address);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    customer = new Customer(
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
        }
        catch (SQLException e) {
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        if (customers.isEmpty()) {
            System.out.println("No customer matches search parameters");
        }
        else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }

        }
        customers.clear();
    }

    // Search by phone
    public void getCustomerByPhone(String phone) {
        String sql = "SELECT * FROM customers WHERE phone LIKE ?";
        phone = "%" + phone + "%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    customer = new Customer(
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
        }
        catch (SQLException e) {
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        if (customers.isEmpty()) {
            System.out.println("No customer matches search parameters");
        }
        else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }

        }
        customers.clear();
    }

    // Search by email
    public void getCustomerByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email LIKE ?";
        email = "%" + email + "%";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    customer = new Customer(
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
        }
        catch (SQLException e) {
            logger.error("An error occurred while searching for customers: {}", e.getMessage(), e);
        }
        if (customers.isEmpty()) {
            System.out.println("No customer matches search parameters");
        }
        else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }

        }
        customers.clear();
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
            logger.error("An error occurred while attempting to edit customer: {}", e.getMessage(), e);
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
            logger.error("An error occurred while attempting to edit customer: {}", e.getMessage(), e);
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
            logger.error("An error occurred while attempting to edit customer: {}", e.getMessage(), e);
        }
    }

    // Edit phone
    public void editCustomerPhone(String phone, String id) {
        String sql = "UPDATE customers SET phone = ? WHERE custId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            boolean validPhone = Validator.numberValidator(phone, 1000000000, 9999999999L);
            if (!validPhone) {
                System.out.println("Not a valid phone number");
                throw new IllegalArgumentException("Not a valid number");
            }
            else {
                statement.setString(1, phone);
                statement.setString(2, id);
                statement.executeUpdate();
            }
        }
        catch (SQLException e) {
            logger.error("An error occurred while attempting to edit customer: {}", e.getMessage(), e);
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
            logger.error("An error occurred while attempting to edit customer: {}", e.getMessage(), e);
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
            logger.error("An error occurred while attempting to edit customer: {}", e.getMessage(), e);
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
            logger.error("An error occurred while attempting to delete customer: {}", e.getMessage(), e);
        }
    }
}
