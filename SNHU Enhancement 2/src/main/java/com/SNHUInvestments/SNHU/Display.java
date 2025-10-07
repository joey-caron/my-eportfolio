package com.SNHUInvestments.SNHU;

//Display class for SNHU Investments
//Houses main method
//Created by Joseph Caron

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public Display() {
        // Setting up the main window
        setTitle("SNHU Investments");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
                switchPanels(loginPanel);
            }
        });
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(regPanel);
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
        JTextArea loginOut = new JTextArea();

        // Action for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                char[] password = passField.getPassword();
                boolean login = log.login(username, password);
                if (login) {
                    switchPanels(menuPanel);
                }
                else {
                    loginOut.setText("");
                    loginOut.setText("Invalid username or password");
                    throw new SecurityException("Invalid username or password");
                }
            }
        });

        // Set alignment and add components to panel
        userField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passField.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(userField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passField);
        loginPanel.add(loginButton);
        loginPanel.add(loginOut);

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
        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(200,20));
        nameField.setMaximumSize(nameField.getPreferredSize());
        JTextField jobField = new JTextField(20);
        jobField.setPreferredSize(new Dimension(200,20));
        jobField.setMaximumSize(jobField.getPreferredSize());
        JButton register = new JButton("Register");
        JButton regBack = new JButton("Back");
        JTextArea regOut = new JTextArea();

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
            else {
                crud.addUser(user);
                System.out.println("User added successfully. Go back to login.");
            }

        });
        regBack.addActionListener(e -> switchPanels(startPanel));

        // Set alignment and add components to panel
        newUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        newPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        jobField.setAlignmentX(Component.LEFT_ALIGNMENT);
        register.setAlignmentX(Component.LEFT_ALIGNMENT);
        regBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        regPanel.add(new JLabel("Username:"));
        regPanel.add(newUser);
        regPanel.add(new JLabel("Password:"));
        regPanel.add(newPass);
        regPanel.add(new JLabel("Name:"));
        regPanel.add(nameField);
        regPanel.add(new JLabel("Job Title:"));
        regPanel.add(jobField);
        regPanel.add(register);
        regPanel.add(regBack);
        regPanel.add(regOut);

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
                switchPanels(addPanel);
            }
        });
        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(searchPanel);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(editPanel);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(deletePanel);
            }
        });
        logout.addActionListener(e -> {
            log.logout();
            switchPanels(startPanel);
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
        JTextArea addOut = new JTextArea();

        // Action for Submit and Back buttons
        submit.addActionListener(e -> {
            String customerId = custId.getText();
            String custFirst = firstName.getText();
            String custLast = lastName.getText();
            String custAddress = address.getText();
            String custPhone = phone.getText();
            String custEmail = email.getText();
            String custStatus = Objects.requireNonNull(status.getSelectedItem()).toString();
            Customer customer = new Customer(customerId, custFirst, custLast, custAddress,
                    custPhone, custEmail, custStatus);
            if (crud.searchCustById(customerId)) {
                System.out.println("Customer ID already in use.");
            }
            else {
                crud.addCustomer(customer);
                System.out.println("Customer added successfully.");
            }

        });
        addBack.addActionListener(e -> switchPanels(menuPanel));

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
        addPanel.add(addOut);

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
        JTextArea searchOut = new JTextArea();

        //Action for Search and Back buttons
        search.addActionListener(e -> {
            String choice = Objects.requireNonNull(searchField.getSelectedItem()).toString();
            String searchValue = input.getText();
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
        searchBack.addActionListener(e -> switchPanels(menuPanel));

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
        searchPanel.add(searchOut);

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
        JTextArea editOut = new JTextArea();

        // Action for Edit and Back buttons
        edit.addActionListener(e -> {
            String customerId1 = custId.getText();
            String column = Objects.requireNonNull(editField.getSelectedItem()).toString();
            String status1 = Objects.requireNonNull(statusOpt.getSelectedItem().toString());
            String value1 = input.getText();
            if (!crud.searchCustById(customerId1)) {
                System.out.println("Customer ID not found");
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
                System.out.println("Customer updated successfully.");
            }

        });
        editBack.addActionListener(e -> switchPanels(menuPanel));

        // Set alignment and add components to panel
        customerId.setAlignmentX(Component.LEFT_ALIGNMENT);
        editField.setAlignmentX(Component.LEFT_ALIGNMENT);
        value.setAlignmentX(Component.LEFT_ALIGNMENT);
        edit.setAlignmentX(Component.LEFT_ALIGNMENT);
        editBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        editPanel.add(new JLabel("ID of customer to edit:"));
        editPanel.add(customerId);
        editPanel.add(new JLabel("Item to edit:"));
        editPanel.add(editField);
        editPanel.add(new JLabel("New value:"));
        editPanel.add(value);
        editPanel.add(edit);
        editPanel.add(editBack);
        editPanel.add(editOut);

        // Create delete panel
        deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
        JTextField deleteId = new JTextField(10);
        deleteId.setPreferredSize(new Dimension(200,20));
        deleteId.setMaximumSize(deleteId.getPreferredSize());
        JButton deleteCust = new JButton("Delete");
        JButton deleteBack = new JButton("Back");
        JTextArea delOut = new JTextArea();

        // Action for Delete and Back buttons
        deleteCust.addActionListener(e -> {
            String customerId2 = custId.getText();
            if (!crud.searchCustById(customerId2)) {
                System.out.println("Customer ID not found.");
            }
            else {
                crud.deleteCustomer(customerId2);
                System.out.println("Customer deleted successfully.");
            }
        });
        deleteBack.addActionListener(e -> switchPanels(menuPanel));

        // Set alignment and add components to panel
        deleteId.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteCust.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        deletePanel.add(new JLabel("Enter ID of customer:"));
        deletePanel.add(deleteId);
        deletePanel.add(new JLabel("WARNING: THIS ACTION CANNOT BE UNDONE:"));
        deletePanel.add(deleteCust);
        deletePanel.add(deleteBack);
        deletePanel.add(delOut);

        getContentPane().add(startPanel); // Set initial panel
        setVisible(true);

    }

    private void switchPanels(JPanel newPanel) {
        getContentPane().removeAll(); // clear screen
        getContentPane().add(newPanel); // add new screen
        revalidate(); // re-layout components
        repaint(); // repaint the frame
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(Display::new);
    }

}
