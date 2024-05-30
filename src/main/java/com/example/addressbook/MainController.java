package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainController {
    private MockContactDAO contactDAO;

    public MainController() {
        this.contactDAO = new MockContactDAO();
    }
    @FXML
    public ListView<Contact> contactsListView;

    @FXML
    public void initialize() {
        contactsListView.setCellFactory(contactListView -> new CustomContactCell());

        // synchronizes the contacts list view with the contacts in the database
        // and should be called whenever the contacts in the database change
        contactsListView.getItems().clear();
        contactsListView.getItems().addAll(contactDAO.getAllContacts());
    }
}
