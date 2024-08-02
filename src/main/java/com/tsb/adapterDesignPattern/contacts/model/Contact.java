package com.tsb.adapterDesignPattern.contacts.model;

/**
 * The Contact class models the information of an individual contact.
 * It holds the contact's full name, email address, phone number, and friendship status.
 */
public class Contact {
    // Fields to store the contact's details
    private String fullName;      // Contact's full name
    private String email;         // Contact's email address
    private String phoneNumber;   // Contact's phone number
    private boolean friend;       // Flag indicating whether the contact is considered a friend

    /**
     * Constructs a new Contact object with the specified details.
     * 
     * @param fullName the full name of the contact
     * @param email the email address of the contact
     * @param phoneNumber the phone number of the contact
     * @param friend a boolean indicating if the contact is a friend (true) or not (false)
     */
    public Contact(String fullName, String email, String phoneNumber, boolean friend) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.friend = friend;
    }

    /**
     * Returns the full name of the contact.
     * 
     * @return the full name of the contact
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns the email address of the contact.
     * 
     * @return the email address of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phone number of the contact.
     * 
     * @return the phone number of the contact
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns whether the contact is marked as a friend.
     * 
     * @return true if the contact is a friend, false otherwise
     */
    public boolean isFriend() {
        return friend;
    }

    /**
     * Returns a string representation of the Contact object, which includes all details
     * such as name, email, phone number, and friendship status.
     * This method is particularly useful for logging and displaying contact information in a user-readable format.
     * 
     * @return a string representation of the Contact
     */
    @Override
    public String toString() {
        return "Contact{" +
               "fullName='" + fullName + '\'' +
               ", email='" + email + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", friend=" + (friend ? "Yes" : "No") +
               '}';
    }
}
