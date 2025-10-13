package com.SNHUInvestments.SNHU;

//Validator class for SNHU Investments
//Created by Joseph Caron

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean numberValidator(String userNum, long minNum, long maxNum) {
        long userLong;

        try {
            userLong = Long.parseLong(userNum);
            if (userLong < minNum || userLong > maxNum) { // Make sure the number is within range
                System.out.println("Number not within acceptable range.");
                throw new IllegalArgumentException("Number not within acceptable range.");
            }
        }
        catch (NumberFormatException e) { // Make sure the number entered is a number
            System.err.println("Error: Invalid number.");
            return false;
        }
        return true;
    }

    public static boolean passwordValidator(char[] password) {
        //Make sure password is long enough
        if (password.length < 8) {
            System.out.println("Password must contain at least 8 characters");
            return false;
        }

        // Check for uppercase letters in password
        boolean hasUpper = false;
        for (char c : password) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
                break;
            }
        }
        if (!hasUpper) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }

        // Check for lowercase letters in password
        boolean hasLower = false;
        for (char c : password) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
                break;
            }
        }
        if (!hasLower) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }

        // Check for numbers in password
        boolean hasDigit = false;
        for (char c : password) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit) {
            System.out.println("Password must contain at least one number");
            return false;
        }

        // Check for special characters
        boolean hasSpecial = false;
        String specials = "!@#$%^&*";
        for (char c : password) {
            if (specials.contains(String.valueOf(c))) {
                hasSpecial = true;
                break;
            }
        }
        if (!hasSpecial) {
            System.out.println("Password must contain at least one special character !@#$%^&*");
            return false;
        }

        // Check for invalid characters
        String invalids = "`~()-+=_[]{}|/?.>,<';:\"\\";
        for (char c : password) {
            if (invalids.contains(String.valueOf(c))) {
                System.out.println("Password can only contain alphanumeric characters and special characters !@#$%^&*");
                return false;
            }

        }

        // All rules pass
        return true;
    }

    public static boolean usernameValidator(String username) {

        // Ensure username is right length
        if (username.length() < 8 || username.length() > 16) {
            System.out.println("Username must be between 8 and 16 characters");
            return false;
        }

        // Regex pattern checker to make sure username is alphanumeric only
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            System.out.println("Username must contain only letters and numbers");
        }

        // Makes sure first character of username is a letter
        char c = username.charAt(0);
        if (Character.isDigit(c)) {
            System.out.println("Username must start with a letter");
            return false;
        }

        return true;
    }


}
