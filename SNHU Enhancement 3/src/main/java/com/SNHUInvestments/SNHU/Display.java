package com.SNHUInvestments.SNHU;

//Display class for SNHU Investments
//Houses main method
//Created by Joseph Caron

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;


public class Display extends JFrame {

    // Initializing various display panels
    private final JPanel loginPanel;
    private final JPanel regPanel;
    private final JPanel menuPanel;
    private final JPanel startPanel;
    private final JPanel addPanel;
    private final JPanel searchPanel;
    private final JPanel editPanel;
    private final JPanel deletePanel;
    Login log = new Login();
    CRUD crud = new CRUD();


    // Method to show UI display with various panels
    public Display() {
        // Setting up the main window
        setTitle("SNHU Investments");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(2,1));

        // Create bottom half
        JPanel bottomPanel = new JPanel();
        JTextArea output = new JTextArea(10,50);
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);

        bottomPanel.add(scroll);
        // Set up so console output writes to text area
        PrintStream printStream = new PrintStream(new TextAreaOutput(output));
        System.setOut(printStream);



        // Create start panel
        startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.add(new JLabel("Please Log In or Register"));
        JButton logButton = new JButton("Login");
        JButton regButton = new JButton("Register");

        // Action for Login and Register buttons
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(loginPanel, bottomPanel);
                output.setText(null); // Clear the output field
            }

        });
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(regPanel, bottomPanel);
                output.setText(null); // Clear the output field
            }
        });

        // Set alignment and add components to panel
        logButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        regButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        startPanel.add(logButton);
        startPanel.add(regButton);



        // Create login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        JTextField userField = new JTextField(20);
        userField.setPreferredSize(new Dimension(200,20));
        userField.setMaximumSize(userField.getPreferredSize());
        JPasswordField passField = new JPasswordField(20);
        passField.setEchoChar('*');
        passField.setPreferredSize(new Dimension(200,20));
        passField.setMaximumSize(passField.getPreferredSize());
        JButton loginButton = new JButton("Login");
        JButton loginBack = new JButton("Back");

        // Action for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                char[] password = passField.getPassword();
                boolean login = log.login(username, password);
                if (login) {
                    switchPanels(menuPanel, bottomPanel);
                    output.setText(null); // Clear the output field
                }
                else {
                    System.out.println("Invalid username or password.");
                    throw new SecurityException("Invalid username or password.");
                }
            }
        });
        passField.addKeyListener(new KeyListener() { // After typing password, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        loginBack.addActionListener(e -> {
            switchPanels(startPanel, bottomPanel);
            output.setText(null); // Clear the output field
        });


        // Set alignment and add components to panel
        userField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passField.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(userField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passField);
        loginPanel.add(loginButton);
        loginPanel.add(loginBack);
        getRootPane().setDefaultButton(loginButton);


        // Create register panel
        regPanel = new JPanel();
        regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.Y_AXIS));
        JTextField newUser = new JTextField(20);
        newUser.setPreferredSize(new Dimension(200,20));
        newUser.setMaximumSize(newUser.getPreferredSize());
        JPasswordField newPass = new JPasswordField(20);
        newPass.setEchoChar('*');
        newPass.setPreferredSize(new Dimension(200,20));
        newPass.setMaximumSize(newPass.getPreferredSize());
        JPasswordField confPass = new JPasswordField(20);
        confPass.setEchoChar('*');
        confPass.setPreferredSize(new Dimension(200,20));
        confPass.setMaximumSize(newPass.getPreferredSize());
        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(200,20));
        nameField.setMaximumSize(nameField.getPreferredSize());
        JTextField jobField = new JTextField(20);
        jobField.setPreferredSize(new Dimension(200,20));
        jobField.setMaximumSize(jobField.getPreferredSize());
        JButton register = new JButton("Register");
        JButton regBack = new JButton("Back");

        // Action for Register and Back buttons
        register.addActionListener(e -> {
            String username = newUser.getText();
            char[] password = newPass.getPassword();
            String name = nameField.getText();
            String jobTitle = jobField.getText();
            User user = new User(username, password, name, jobTitle);
            if (crud.searchUserByUsername(username)) {
                System.out.println("Username already exists, choose a different username.");
            }
            else if (!Objects.equals(Arrays.toString(newPass.getPassword()), Arrays.toString(confPass.getPassword()))) {
                System.out.println("Passwords do not match.");
            }
            else if (Objects.equals(name, "")) {
                System.out.println("Name cannot be blank.");
            }
            else if (Objects.equals(jobTitle, "")) {
                System.out.println("Job Title cannot be blank");
            }
            else {
                crud.addUser(user);
                System.out.println("User added successfully. Go back to login.");
            }

        });
        newUser.addKeyListener(new KeyListener() { // After entering username, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    register.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        newPass.addKeyListener(new KeyListener() { // After entering password, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    register.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        confPass.addKeyListener(new KeyListener() { // After entering password, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    register.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        nameField.addKeyListener(new KeyListener() { // After entering name, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    register.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        jobField.addKeyListener(new KeyListener() { // After entering job field, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    register.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        regBack.addActionListener(e -> {
            switchPanels(startPanel, bottomPanel);
            output.setText(null); // Clear the output field
        });

        // Set alignment and add components to panel
        newUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        newPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        confPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        jobField.setAlignmentX(Component.LEFT_ALIGNMENT);
        register.setAlignmentX(Component.LEFT_ALIGNMENT);
        regBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(new JLabel("Username:"));
        regPanel.add(newUser);
        regPanel.add(new JLabel("Password:"));
        regPanel.add(newPass);
        regPanel.add(new JLabel("Confirm Password:"));
        regPanel.add(confPass);
        regPanel.add(new JLabel("Name:"));
        regPanel.add(nameField);
        regPanel.add(new JLabel("Job Title:"));
        regPanel.add(jobField);
        regPanel.add(register);
        regPanel.add(regBack);
        getRootPane().setDefaultButton(register);

        // Create menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JButton create = new JButton("Add Client");
        JButton read = new JButton("Search Clients");
        JButton update = new JButton("Edit Clients");
        JButton delete = new JButton("Delete Clients");
        JButton logout = new JButton("Log Out");
        JButton exit = new JButton("Exit");


        // Action for Add, Search, Edit, Delete, Log Out and Exit buttons
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(addPanel, bottomPanel);
            }
        });
        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(searchPanel, bottomPanel);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(editPanel, bottomPanel);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(deletePanel, bottomPanel);
            }
        });
        logout.addActionListener(e -> {
            log.logout();
            switchPanels(startPanel, bottomPanel);
        });
        exit.addActionListener(e -> System.exit(0));

        // Set alignment and add components to panel
        create.setAlignmentX(Component.LEFT_ALIGNMENT);
        read.setAlignmentX(Component.LEFT_ALIGNMENT);
        update.setAlignmentX(Component.LEFT_ALIGNMENT);
        delete.setAlignmentX(Component.LEFT_ALIGNMENT);
        logout.setAlignmentX(Component.LEFT_ALIGNMENT);
        exit.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuPanel.add(new JLabel("Please select an option:"));
        menuPanel.add(create);
        menuPanel.add(read);
        menuPanel.add(update);
        menuPanel.add(delete);
        menuPanel.add(logout);
        menuPanel.add(exit);

        // Create add panel
        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        JTextField custId = new JTextField(10);
        custId.setPreferredSize(new Dimension(200,20));
        custId.setMaximumSize(custId.getPreferredSize());
        JTextField firstName = new JTextField(20);
        firstName.setPreferredSize(new Dimension(200,20));
        firstName.setMaximumSize(firstName.getPreferredSize());
        JTextField lastName = new JTextField(20);
        lastName.setPreferredSize(new Dimension(200,20));
        lastName.setMaximumSize(lastName.getPreferredSize());
        JTextField address = new JTextField(40);
        address.setPreferredSize(new Dimension(200,20));
        address.setMaximumSize(address.getPreferredSize());
        JTextField phone = new JTextField(20);
        phone.setPreferredSize(new Dimension(200,20));
        phone.setMaximumSize(phone.getPreferredSize());
        JTextField email = new JTextField(20);
        email.setPreferredSize(new Dimension(200,20));
        email.setMaximumSize(email.getPreferredSize());
        String[] choices = {"Brokerage", "Retirement"};
        JComboBox<String> status = new JComboBox<>(choices);
        status.setSelectedIndex(0);
        status.setPreferredSize(new Dimension(200,20));
        status.setMaximumSize(status.getPreferredSize());
        JButton submit = new JButton("Submit");
        JButton addBack = new JButton("Back");

        // Action for Submit and Back buttons
        submit.addActionListener(e -> {
            output.setText(null); // Clear the output field
            String customerId = custId.getText();
            String custFirst = firstName.getText();
            String custLast = lastName.getText();
            String custAddress = address.getText();
            String custPhone = phone.getText();
            String custEmail = email.getText();
            String custStatus = Objects.requireNonNull(status.getSelectedItem()).toString();
            boolean validPhone = Validator.numberValidator(custPhone, 1000000000, 9999999999L);

            if (crud.searchCustById(customerId)) {
                System.out.println("Customer ID already in use.");
            }
            else if (Objects.equals(customerId, "")) {
                System.out.println("Customer ID cannot be blank.");
                throw new IllegalArgumentException("Customer ID cannot be blank");
            }
            else if (Objects.equals(custFirst, "")) {
                System.out.println("First name cannot be blank.");
                throw new IllegalArgumentException("First name cannot be blank.");
            }
            else if (Objects.equals(custLast, "")) {
                System.out.println("Last name cannot be blank.");
                throw new IllegalArgumentException("Last name cannot be blank");
            }
            else if (Objects.equals(custAddress, "")) {
                System.out.println("Address cannot be blank.");
                throw new IllegalArgumentException("Address cannot be blank.");
            }
            else if (Objects.equals(custPhone, "")) {
                System.out.println("Phone cannot be blank.");
            }
            else if (!validPhone) {
                System.out.println("Not a valid phone number");
                throw new IllegalArgumentException("Not a valid number");
            }
            else if (Objects.equals(custEmail, "")) {
                System.out.println("Email cannot be blank.");
                throw new IllegalArgumentException("Email cannot be blank");
            }
            Customer customer = new Customer(customerId, custFirst, custLast, custAddress,
                    custPhone, custEmail, custStatus);
            crud.addCustomer(customer);
            System.out.println("Customer " + customerId + " added successfully.");


        });
        custId.addKeyListener(new KeyListener() { // After entering customer ID, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        firstName.addKeyListener(new KeyListener() { // After entering first name, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        lastName.addKeyListener(new KeyListener() { // After entering last name, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        address.addKeyListener(new KeyListener() { // After entering address, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        phone.addKeyListener(new KeyListener() { // After entering phone number, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        email.addKeyListener(new KeyListener() { // After entering email, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        addBack.addActionListener(e -> {
            switchPanels(menuPanel, bottomPanel);
            output.setText(null); // Clear the output field
        });

        // Set alignment and add components to panel
        custId.setAlignmentX(Component.LEFT_ALIGNMENT);
        firstName.setAlignmentX(Component.LEFT_ALIGNMENT);
        lastName.setAlignmentX(Component.LEFT_ALIGNMENT);
        address.setAlignmentX(Component.LEFT_ALIGNMENT);
        phone.setAlignmentX(Component.LEFT_ALIGNMENT);
        email.setAlignmentX(Component.LEFT_ALIGNMENT);
        status.setAlignmentX(Component.LEFT_ALIGNMENT);
        submit.setAlignmentX(Component.LEFT_ALIGNMENT);
        addBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        addPanel.add(new JLabel("Customer ID"));
        addPanel.add(custId);
        addPanel.add(new JLabel("First Name"));
        addPanel.add(firstName);
        addPanel.add(new JLabel("Last Name"));
        addPanel.add(lastName);
        addPanel.add(new JLabel("Address"));
        addPanel.add(address);
        addPanel.add(new JLabel("Phone Number"));
        addPanel.add(phone);
        addPanel.add(new JLabel("Email Address"));
        addPanel.add(email);
        addPanel.add(new JLabel("Status"));
        addPanel.add(status);
        addPanel.add(submit);
        addPanel.add(addBack);
        getRootPane().setDefaultButton(submit);

        // Create search panel
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        String[] searchChoices = {"Customer ID", "First Name", "Last Name", "Address", "Phone", "Email"};
        JComboBox<String> searchField = new JComboBox<>(searchChoices);
        searchField.setPreferredSize(new Dimension(200,20));
        searchField.setMaximumSize(searchField.getPreferredSize());
        JTextField input = new JTextField(20);
        input.setPreferredSize(new Dimension(200,20));
        input.setMaximumSize(input.getPreferredSize());
        JButton search = new JButton("Search");
        searchField.setSelectedIndex(0);
        JButton searchBack = new JButton("Back");

        //Action for Search and Back buttons
        search.addActionListener(e -> {
            String choice = Objects.requireNonNull(searchField.getSelectedItem()).toString();
            String searchValue = input.getText();
            output.setText(null); // Clear the output field
            if (Objects.equals(searchValue, "")) {
                crud.getAllCustomers();
            }
            else {
                if (Objects.equals(choice, "Customer ID")) {
                    crud.getCustomerById(searchValue);
                } else if (Objects.equals(choice, "First Name")) {
                    crud.getCustomerByFirst(searchValue);
                } else if (Objects.equals(choice, "Last Name")) {
                    crud.getCustomerByLast(searchValue);
                } else if (Objects.equals(choice, "Address")) {
                    crud.getCustomerByAddress(searchValue);
                } else if (Objects.equals(choice, "Phone")) {
                    crud.getCustomerByPhone(searchValue);
                } else if (Objects.equals(choice, "Email")) {
                    crud.getCustomerByEmail(searchValue);
                }
            }
        });
        input.addKeyListener(new KeyListener() { // After entering input, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        searchBack.addActionListener(e -> {
            switchPanels(menuPanel, bottomPanel);
            output.setText(null); // Clear the output field
        });

        // Set alignment and add components to panel
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        input.setAlignmentX(Component.LEFT_ALIGNMENT);
        search.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.add(new JLabel("Search via:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Enter text:"));
        searchPanel.add(input);
        searchPanel.add(search);
        searchPanel.add(searchBack);
        getRootPane().setDefaultButton(search);

        // Create edit panel
        editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        JTextField customerId = new JTextField(10);
        customerId.setPreferredSize(new Dimension(200,20));
        customerId.setMaximumSize(customerId.getPreferredSize());
        String[] editChoices = {"First Name", "Last Name", "Address", "Phone", "Email", "Status"};
        JComboBox<String> editField = new JComboBox<>(editChoices);
        editField.setPreferredSize(new Dimension(200,20));
        editField.setMaximumSize(editField.getPreferredSize());
        String[] statusChoices = {"Brokerage", "Retirement"};
        JComboBox<String> statusOpt = new JComboBox<>(statusChoices);
        statusOpt.setPreferredSize(new Dimension(200, 20));
        statusOpt.setMaximumSize(statusOpt.getPreferredSize());
        JTextField value = new JTextField(20);
        value.setPreferredSize(new Dimension(200,20));
        value.setMaximumSize(value.getPreferredSize());
        JButton edit = new JButton("Submit Changes");
        editField.setSelectedIndex(0);
        statusOpt.setSelectedIndex(0);
        JButton editBack = new JButton("Back");

        // Action for Edit and Back buttons
        edit.addActionListener(e -> {
            output.setText(null); // Clear the output field
            String customerId1 = customerId.getText();
            String column = Objects.requireNonNull(editField.getSelectedItem()).toString();
            String status1 = Objects.requireNonNull(statusOpt.getSelectedItem()).toString();
            String value1 = value.getText();
            if (!crud.searchCustById(customerId1)) {
                System.out.println("Customer ID not found");
            }
            else if (Objects.equals(value1, "") && !Objects.equals(column, "Status")) {
                System.out.println("Value cannot be blank.");
            }
            else {
                if (Objects.equals(column, "First Name")) {
                    crud.editCustomerFirst(value1, customerId1);
                }
                else if (Objects.equals(column, "Last Name")) {
                    crud.editCustomerLast(value1, customerId1);
                }
                else if (Objects.equals(column,"Address")) {
                    crud.editCustomerAddress(value1, customerId1);
                }
                else if (Objects.equals(column, "Phone")) {
                    crud.editCustomerPhone(value1, customerId1);
                }
                else if (Objects.equals(column, "Email")) {
                    crud.editCustomerEmail(value1, customerId1);
                }
                else if (Objects.equals(column, "Status")) {
                    crud.editCustomerStatus(status1, customerId1);
                }
                System.out.println("Customer " + customerId1 + " updated successfully.");
            }

        });
        customerId.addKeyListener(new KeyListener() { // After entering customer ID, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    edit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        value.addKeyListener(new KeyListener() { // After entering value, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    edit.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        editBack.addActionListener(e -> {
            switchPanels(menuPanel, bottomPanel);
            output.setText(null); // Clear the output field
        });

        // Set alignment and add components to panel
        customerId.setAlignmentX(Component.LEFT_ALIGNMENT);
        editField.setAlignmentX(Component.LEFT_ALIGNMENT);
        value.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusOpt.setAlignmentX(Component.LEFT_ALIGNMENT);
        edit.setAlignmentX(Component.LEFT_ALIGNMENT);
        editBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        editPanel.add(new JLabel("ID of customer to edit:"));
        editPanel.add(customerId);
        editPanel.add(new JLabel("Item to edit:"));
        editPanel.add(editField);
        editPanel.add(new JLabel("New value:"));
        editPanel.add(value);
        editPanel.add(new JLabel("If changing status, choose option:"));
        editPanel.add(statusOpt);
        editPanel.add(edit);
        editPanel.add(editBack);
        getRootPane().setDefaultButton(edit);

        // Create delete panel
        deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
        JTextField deleteId = new JTextField(10);
        deleteId.setPreferredSize(new Dimension(200,20));
        deleteId.setMaximumSize(deleteId.getPreferredSize());
        JButton deleteCust = new JButton("Delete");
        JButton deleteBack = new JButton("Back");

        // Action for Delete and Back buttons
        deleteCust.addActionListener(e -> {
            output.setText(null); // Clear the output field
            String customerId2 = deleteId.getText();
            if (!crud.searchCustById(customerId2)) {
                System.out.println("Customer ID not found.");
            }
            else {
                crud.deleteCustomer(customerId2);
                System.out.println("Customer " + customerId2 + " deleted successfully.");
            }
        });
        deleteId.addKeyListener(new KeyListener() { // After entering customer ID, hitting enter will submit
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    deleteCust.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        deleteBack.addActionListener(e -> {
            switchPanels(menuPanel, bottomPanel);
            output.setText(null); // Clear the output field
        });

        // Set alignment and add components to panel
        deleteId.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteCust.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        deletePanel.add(new JLabel("Enter ID of customer:"));
        deletePanel.add(deleteId);
        deletePanel.add(new JLabel("WARNING: THIS ACTION CANNOT BE UNDONE:"));
        deletePanel.add(deleteCust);
        deletePanel.add(deleteBack);
        getRootPane().setDefaultButton(deleteCust);

        getContentPane().add(startPanel); // Set initial panel
        getContentPane().add(bottomPanel);
        setVisible(true);

    }

    // Method to switch panels
    private void switchPanels(JPanel newPanel, JPanel bottomPanel) {
        getContentPane().removeAll(); // clear screen
        getContentPane().add(newPanel); // add new screen
        getContentPane().add(bottomPanel);
        clearFields(newPanel);
        revalidate(); // re-layout components
        repaint(); // repaint the frame
    }

    // Make text entry fields empty on page load
    private static void clearFields(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField textField) {
                textField.setText("");
            }
        }
    }

}
