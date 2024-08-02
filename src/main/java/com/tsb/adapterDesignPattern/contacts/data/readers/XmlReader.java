package com.tsb.adapterDesignPattern.contacts.data.readers;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.util.List;
import java.util.ArrayList;

/**
 * XmlReader is used to parse XML content and extract contact information.
 * It uses the Java API for XML Processing (JAXP) to parse XML.
 */
public class XmlReader {
    /**
     * Parses XML content to extract contact details into a list of strings.
     * Each string is a formatted representation of a contact: "fullname, email, phoneNumber, friend".
     *
     * @param xmlContent The XML string containing the contacts.
     * @return A list of strings, each representing a contact's details formatted for easy use.
     */
    public List<String> readData(String xmlContent) {
        List<String> data = new ArrayList<>();
        try {
            // Setup for XML parsing
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlContent));
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();

            // Extract contact elements from the XML document.
            NodeList nList = document.getElementsByTagName("contact");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // Concatenate contact details into a single string with comma separation.
                    String contact = eElement.getElementsByTagName("fullname").item(0).getTextContent() + ", " +
                                     eElement.getElementsByTagName("email").item(0).getTextContent() + ", " +
                                     eElement.getElementsByTagName("phoneNumber").item(0).getTextContent() + ", " +
                                     eElement.getElementsByTagName("friend").item(0).getTextContent();
                    data.add(contact);  // Add the formatted string to the list.
                }
            }
        } catch (Exception e) {  // Handle any parsing exceptions.
            e.printStackTrace();
        }
        return data;  // Return the list of formatted contact strings.
    }
}
