package com.tsb.adapterDesignPattern.contacts.data.adapters;

// Importing necessary Java and project-specific classes.
import java.util.List;
import java.util.stream.Collectors;
import com.tsb.adapterDesignPattern.contacts.data.readers.JsonReader;
import com.tsb.adapterDesignPattern.contacts.model.Contact;

/**
 * JsonAdapter is responsible for adapting JSON formatted data into a list of Contact objects.
 * This adapter utilizes a JsonReader to read and extract raw contact data from a JSON source,
 * and then transforms that data into Contact objects.
 */
public class JsonAdapter implements ContactsAdapter {
    private JsonReader jsonReader;  // Dependency on JsonReader to handle JSON data extraction.

    /**
     * Constructs a JsonAdapter with a specific JsonReader.
     * @param jsonReader The JsonReader used to extract raw data from JSON files.
     */
    public JsonAdapter(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    /**
     * Retrieves contact data from a JSON file and converts it into a list of Contact objects.
     * 
     * @param filePath The path to the JSON file containing the raw contact data.
     * @return A list of Contact objects populated with data from the JSON file.
     */
    @Override
    public List<Contact> getContacts(String filePath) {
        // Use the JsonReader to get a list of raw contact data strings from the JSON file.
        List<String> rawData = jsonReader.readData(filePath);
        
        // Convert each raw data string into a Contact object.
        return rawData.stream().map(line -> {
            // Split the raw data string into its constituent parts.
            String[] parts = line.split(", ");
            // Create a new Contact object using the parts. Assumes that the parts array is correctly ordered.
            return new Contact(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
        }).collect(Collectors.toList());  // Collect all the Contact objects into a list.
    }
}
