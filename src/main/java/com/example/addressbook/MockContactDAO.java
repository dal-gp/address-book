package com.example.addressbook;

import java.util.ArrayList;
import java.util.List;

public class MockContactDAO implements IContactDAO{
    private List<Contact> contacts;
    public static int autoIncrementedId = 0;

    public MockContactDAO() {
        this.contacts = new ArrayList<>();
        addContact(new Contact(
                "John", "Doe", "Johndoe@example.com", "0423423423"));
        addContact(new Contact(
                "Jane", "Doe", "Janedoe@example.com", "0423423424"));
        addContact(new Contact(
                "Jay", "Doe", "Jaydoe@example.com", "0423423425"));
    }

    @Override
    public void addContact(Contact contact) {
        contact.setId(autoIncrementedId);
        autoIncrementedId++;
        contacts.add(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        for(int i = 0; i < contacts.size(); i++) {
            if(contact.getId() == contacts.get(0).getId()) {
                contacts.set(i, contact);
                break;
            }
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public Contact getContact(int id) {
        for(Contact contact : contacts) {
            if(id == contact.getId()) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}
