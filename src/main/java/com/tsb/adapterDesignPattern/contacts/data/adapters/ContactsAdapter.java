package com.tsb.adapterDesignPattern.contacts.data.adapters;

// Necessary imports.
import com.tsb.adapterDesignPattern.contacts.model.Contact;

import java.util.List;

/**
 * The ContactsAdapter interface defines a standard way of retrieving contact data from various data sources.
 * Implementing this interface allows different types of data formats (XML, JSON, CSV, etc.) to be adapted 
 * into a uniform list of Contact objects, which can be used seamlessly by the rest of the application.
 * This design promotes flexibility and extensibility in how data is processed and integrated into the application.
 */
public interface ContactsAdapter {

    /**
     * Retrieves a list of contacts from a given data source.
     * 
     * @param data The data from which contacts will be extracted. Depending on the implementation, 
     *             this could be a path to a data file, a string containing the data, or any other form 
     *             suitable for the specific data format being adapted.
     * @return A List of Contact objects representing the contacts found in the data. The list should be 
     *         empty if no contacts are found or if an error occurs during data processing.
     */
    List<Contact> getContacts(String data);
}
