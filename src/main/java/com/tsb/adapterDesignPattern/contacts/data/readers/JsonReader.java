package com.tsb.adapterDesignPattern.contacts.data.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonReader is used to extract and format contact information from a JSON string.
 * It utilizes Jackson, a high-performance JSON processor, to parse JSON.
 */
public class JsonReader {
    /**
     * Parses JSON content and extracts contact data into a list of strings.
     * Each string represents a contact's information formatted as "fullName, email, phoneNumber, friend".
     * 
     * @param jsonContent The JSON string containing the contact data.
     * @return A list of formatted strings representing individual contacts.
     */
    public List<String> readData(String jsonContent) {
        List<String> data = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();  // Create a Jackson ObjectMapper instance.

        try {
            // Parse the JSON content and get the "contacts" array node.
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(jsonContent).get("contacts");
            
            // Iterate over each node in the array.
            arrayNode.forEach(node -> {
                // Concatenate contact details into a single string with comma separation.
                String contact = node.get("fullName").asText() + ", " +
                                 node.get("email").asText() + ", " +
                                 node.get("phoneNumber").asText() + ", " +
                                 node.get("friend").asText();
                data.add(contact);  // Add the formatted string to the list.
            });
        } catch (Exception e) {  // Catch and handle potential exceptions.
            e.printStackTrace();
        }
        return data;  // Return the list of contact strings.
    }
}
