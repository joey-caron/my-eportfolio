package com.SNHUInvestments.SNHU;

//Customer class for SNHU Investments
//Created by Joseph Caron

import java.util.Objects;

public class Customer {
	private String customerId;
	public String firstName = "";
	public String lastName = "";
	public String address = "";
	public String phone = "";
	public String email = "";
	public String status = "";
	
	// Constructor
	public Customer(String customerId, String firstName, String lastName, String address,
			String phone, String email, String status) {
        setCustomerId(customerId);
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setPhone(phone);
		setEmail(email);
		setStatus(status);
				
	}

	// Getters
	public String getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getStatus() {
		return status;
	}
	
	// Setters
    public void setCustomerId(String newCustomerId) {
        if (!Objects.equals(customerId, "")) {
            this.customerId = newCustomerId;
        }
        else {
            throw new IllegalArgumentException("Customer ID cannot be blank");
        }
    }

	public void setFirstName(String newFirstName) {
		if (newFirstName != null) {
			this.firstName = newFirstName;
		}
		else {
			throw new IllegalArgumentException("First name cannot be blank");
		}
	}

	public void setLastName(String newLastName) {
		if (newLastName != null) {
			this.lastName = newLastName;
		}
		else {
			throw new IllegalArgumentException("Last name cannot be blank");
		}
	}

	public void setAddress(String newAddress) {
		if (newAddress != null) {
			this.address = newAddress;
		}
		else {
			throw new IllegalArgumentException("Must include address");
		}
	}

	public void setPhone(String newPhone) {
        boolean validPhone = Validator.numberValidator(newPhone, 1000000000, 9999999999L);
        if (!validPhone) {
            throw new IllegalArgumentException("Not a valid number");
        } else {
            this.phone = newPhone;
        }
    }

	public void setEmail(String newEmail) {
		if (newEmail != null) {
			this.email = newEmail;
		}
		else {
			throw new IllegalArgumentException("Must include email address");
		}
	}

	public void setStatus(String newStatus) {
		if (newStatus != null) {
			this.status = newStatus;
		}
		else {
			throw new IllegalArgumentException("Brokerage status cannot be blank");
		}
	}

    // Make a print statement print the attributes of the object instead of the object itself
    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", First Name: " + firstName + ", Last Name: " + lastName +
                ", Address: " + address + ", Phone: " + phone + ", Email: " + email + ", Status: " + status;
    }
}
