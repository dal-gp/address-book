package com.example.addressbook.controller;

import com.example.addressbook.model.Contact;
import com.example.addressbook.model.MockContactDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

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
    @FXML
    public VBox contactContainer;

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
        List<Contact> contacts = contactDAO.getAllContacts();
        boolean hasContact = !contacts.isEmpty();
        if(hasContact) {
            contactsListView.getItems().addAll(contacts);
        }
        contactContainer.setVisible(hasContact);


        // select first contact on startup and display its information
        contactsListView.getSelectionModel().selectFirst(); // this will only select
        Contact firstContact = contactsListView.getSelectionModel().getSelectedItem();
        if(firstContact != null) {
            firstNameTextField.setText(firstContact.getFirstName());
            lastNameTextField.setText(firstContact.getLastName());
            emailTextField.setText(firstContact.getEmail());
            phoneTextField.setText(firstContact.getPhone());
        }
    }

    private ListCell<Contact> renderCell(ListView<Contact> contactsListView) {
        return new ListCell<>(){

            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                if(empty || contact == null || contact.getFullName() == null) {
                    setText(null);
//                    super.setOnMouseClicked(e -> onContactSelected(e));
                    super.setOnMouseClicked(mouseEvent -> {
//                        ListCell<Contact> clickedCell = (ListCell<Contact>) mouseEvent.getSource();
//                        Contact selectedContact = clickedCell.getItem();
                        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
                        if(selectedContact != null) {
//                            contactsListView.getSelectionModel().select(selectedContact);
                            firstNameTextField.setText(selectedContact.getFirstName());
                            lastNameTextField.setText(selectedContact.getLastName());
                            emailTextField.setText(selectedContact.getEmail());
                            phoneTextField.setText(selectedContact.getPhone());
                        }
                    });
                }else{
                    setText(contact.getFullName());
                }
            }

//            private void onContactSelected(MouseEvent e) {
//                ListCell<Contact> clickedCell = (ListCell<Contact>) e.getSource();
//                Contact selectedContact = clickedCell.getItem();
//                if(selectedContact != null) {
//                    selectContact(selectedContact);
//                }
//            }
        };
    }

    @FXML
    public void onEditConfirm(ActionEvent actionEvent) {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if(selectedContact != null){
            selectedContact.setFirstName(firstNameTextField.getText());
            selectedContact.setLastName(lastNameTextField.getText());
            selectedContact.setEmail(emailTextField.getText());
            selectedContact.setPhone(phoneTextField.getText());

            contactDAO.updateContact(selectedContact);

            Contact currentContact = contactsListView.getSelectionModel().getSelectedItem();

            contactsListView.getItems().clear();

            List<Contact> contacts = contactDAO.getAllContacts();
            contactsListView.getItems().addAll(contacts);
            Contact nextContact = contacts.contains(currentContact) ? currentContact : contacts.get(0);
            contactsListView.getSelectionModel().select(nextContact);
        }
    }

    @FXML
    public void onDelete(ActionEvent actionEvent) {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if(selectedContact != null) {
            contactDAO.deleteContact(selectedContact);

            Contact currentContact = contactsListView.getSelectionModel().getSelectedItem();
            contactsListView.getItems().clear();
            List<Contact> contacts = contactDAO.getAllContacts();
            boolean hasContact = !contacts.isEmpty();
            if(hasContact){
                contactsListView.getItems().addAll(contacts);
                Contact nextContact = contacts.contains(currentContact) ? currentContact : contacts.get(0);
                contactsListView.getSelectionModel().select(nextContact);
                firstNameTextField.setText(nextContact.getFirstName());
                lastNameTextField.setText(nextContact.getLastName());
                emailTextField.setText(nextContact.getEmail());
                phoneTextField.setText(nextContact.getPhone());
            }
            contactContainer.setVisible(hasContact);
        }
    }

    @FXML
    public void onAdd(ActionEvent actionEvent) {
        Contact newContact = new Contact("New", "Contact", ", ", "");
        // add new contact to the database
        contactDAO.addContact(newContact);

        // sync the contacts list view with the contacts in the database
        contactsListView.getItems().clear();
        List<Contact> contacts = contactDAO.getAllContacts();
        boolean hasContact = !contacts.isEmpty();
        if(hasContact){
            contactsListView.getItems().addAll(contactDAO.getAllContacts());
        }
        contactContainer.setVisible(hasContact);

        // select new contact in the list view
        // and focus the first name tet field
        contactsListView.getSelectionModel().select(newContact);
        firstNameTextField.requestFocus();
    }

    @FXML
    public void onCancel(ActionEvent actionEvent) {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if(selectedContact != null) {
            // since contact hasn't been modified,
            // re-select it to refresh the text fields
            contactsListView.getSelectionModel().select(selectedContact);
            firstNameTextField.setText(selectedContact.getFirstName());
            lastNameTextField.setText(selectedContact.getLastName());
            emailTextField.setText(selectedContact.getEmail());
            phoneTextField.setText(selectedContact.getPhone());
        }
    }

//    private void selectContact(Contact contact) {
//        contactsListView.getSelectionModel().select(contact);
//        firstNameTextField.setText(contact.getFirstName());
//        lastNameTextField.setText(contact.getLastName());
//        emailTextField.setText(contact.getEmail());
//        phoneTextField.setText(contact.getPhone());
//    }

}
