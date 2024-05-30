package com.example.addressbook;

import javafx.scene.control.ListCell;

public class CustomContactCell extends ListCell<Contact> {
    @Override
    protected void updateItem(Contact contact, boolean empty) {
        super.updateItem(contact, empty);
        if(empty || contact == null || contact.getFullName() == null) {
            setText(null);
        } else {
            setText(contact.getFullName());
        }
    }
}
