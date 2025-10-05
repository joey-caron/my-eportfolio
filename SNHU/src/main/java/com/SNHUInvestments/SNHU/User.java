package com.SNHUInvestments.SNHU;

//User class for SNHU Investments
//Created by Joseph Caron

import java.util.Arrays;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class User {
    public String username;
    public String name;
    public String jobTitle;
    private String passwordHash;
 
 

    public User(String username, char[] password, String name, String jobTitle) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setJobTitle(jobTitle);
    }

    // getters
    public String getUsername() {
	    return username;
    }
 
    public String getName() {
	     return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    // setters
    public void setUsername(String newUsername) {
        boolean validUser = Validator.usernameValidator(newUsername); //validates that username meets criteria
        if (validUser) {
            this.username = newUsername;
        }
        else {
            throw new IllegalArgumentException("Invalid Username");
        }
    }

    public void setPassword(char[] newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // hashes password
        boolean validPass = Validator.passwordValidator(newPassword); //validates that password meets criteria
        if (validPass) {
            this.passwordHash = passwordEncoder.encode(new String(newPassword)); // only saves a hashed password
            Arrays.fill(newPassword, '0'); // emptying the password buffer
        }
        else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public void setName(String newName) {
	     if (newName != null) {
		     this.name = newName;
    	 }
	     else {
		     throw new IllegalArgumentException("Name cannot be empty");
    	 }
    }
 
    public void setJobTitle(String newJobTitle) {
	     if (newJobTitle != null) {
		     this.jobTitle = newJobTitle;
    	 }
	     else {
		     throw new IllegalArgumentException("Job title cannot be empty");
    	 }
    }

}
