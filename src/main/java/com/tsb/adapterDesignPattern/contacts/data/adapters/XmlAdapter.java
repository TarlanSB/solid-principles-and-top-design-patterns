package com.tsb.adapterDesignPattern.contacts.data.adapters;

// Import necessary classes
import java.util.List;
import java.util.stream.Collectors;
import com.tsb.adapterDesignPattern.contacts.data.readers.XmlReader;
import com.tsb.adapterDesignPattern.contacts.model.Contact;

/**
 * XmlAdapter is designed to adapt XML formatted data into a list of Contact objects.
 * It relies on an XmlReader to extract raw data from an XML source, then transforms that
 * data into a format usable within the application.
 */
public class XmlAdapter implements ContactsAdapter {
    private XmlReader xmlReader;  // The XmlReader dependency used for reading XML data.

    /**
     * Constructor to initialize the XmlAdapter with a specific XmlReader.
     * @param xmlReader The XmlReader that provides the functionality to read raw XML data.
     */
    public XmlAdapter(XmlReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    /**
     * Converts XML data into a list of Contact objects.
     * @param filePath The path to the XML file containing the contact data.
     * @return A list of Contact objects, each representing a contact from the XML file.
     */
    @Override
    public List<Contact> getContacts(String filePath) {
        // Retrieve a list of strings representing the raw contact data from the XML file.
        List<String> rawData = xmlReader.readData(filePath);
        
        // Convert each raw data string into a Contact object using Java Streams.
        return rawData.stream().map(line -> {
            // Split the line based on comma separation to extract individual contact fields.
            String[] parts = line.split(", ");
            // Create a new Contact object using the extracted parts. Assumes correct order and format.
            return new Contact(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
        }).collect(Collectors.toList());  // Collect and return all Contact objects as a list.
    }
}
