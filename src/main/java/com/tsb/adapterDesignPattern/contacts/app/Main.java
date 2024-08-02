package com.tsb.adapterDesignPattern.contacts.app;

import com.tsb.adapterDesignPattern.contacts.data.adapters.ContactsAdapter;
import com.tsb.adapterDesignPattern.contacts.data.adapters.JsonAdapter;
import com.tsb.adapterDesignPattern.contacts.data.adapters.XmlAdapter;
import com.tsb.adapterDesignPattern.contacts.data.readers.JsonReader;
import com.tsb.adapterDesignPattern.contacts.data.readers.XmlReader;
import com.tsb.adapterDesignPattern.contacts.model.Contact;
import com.tsb.adapterDesignPattern.contacts.util.ResourceLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Instance of ResourceLoader to load data
        ResourceLoader loader = new ResourceLoader();
        String xmlData = loader.loadResource("contacts/contacts.xml");
        String jsonData = loader.loadResource("contacts/contacts.json");

        // Readers specific to XML and JSON
        XmlReader xmlReader = new XmlReader();
        JsonReader jsonReader = new JsonReader();

        // Adapters implementing the ContactsAdapter interface
        ContactsAdapter xmlAdapter = new XmlAdapter(xmlReader);
        ContactsAdapter jsonAdapter = new JsonAdapter(jsonReader);

        // Display XML contacts
        System.out.println("XML Contacts:");
        List<Contact> xmlContacts = xmlAdapter.getContacts(xmlData);
        xmlContacts.forEach(System.out::println);

        // Display JSON contacts
        System.out.println("\nJSON Contacts:");
        List<Contact> jsonContacts = jsonAdapter.getContacts(jsonData);
        jsonContacts.forEach(System.out::println);
    }
}



