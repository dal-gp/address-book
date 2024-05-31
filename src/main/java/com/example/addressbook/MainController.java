package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {
    private MockContactDAO contactDAO;

    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField phoneTextField;
    @FXML
    public ListView<Contact> contactsListView;

    public MainController() {
        this.contactDAO = new MockContactDAO();
    }

    @FXML
    public void initialize() {
//        contactsListView.setCellFactory(contactListView -> new CustomContactCell());
        contactsListView.setCellFactory(contactListView -> renderCell(contactListView));

        // synchronizes the contacts list view with the contacts in the database
        // and should be called whenever the contacts in the database change
        contactsListView.getItems().clear();
        contactsListView.getItems().addAll(contactDAO.getAllContacts());
    }

    private ListCell<Contact> renderCell(ListView<Contact> contactsListView) {
        return new ListCell<>(){

            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                if(empty || contact == null || contact.getFullName() == null) {
                    setText(null);
                    super.setOnMouseClicked(e -> onContactSelected(e));
                }else{
                }
            }

            private void onContactSelected(MouseEvent e) {
                ListCell<Contact> clickedCell = (ListCell<Contact>) e.getSource();
                Contact selectedContact = clickedCell.getItem();
                if(selectedContact != null) {
                    selectContact(selectedContact);
                }
            }
        };
    }

    private void selectContact(Contact contact) {
        contactsListView.getSelectionModel().select(contact);
        firstNameTextField.setText(contact.getFirstName());
        lastNameTextField.setText(contact.getLastName());
        emailTextField.setText(contact.getEmail());
        phoneTextField.setText(contact.getPhone());
    }

}
