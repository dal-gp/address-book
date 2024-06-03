package com.example.addressbook.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SqliteContactDAO implements IContactDAO {
    private Connection connection;

    public SqliteContactDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        insertSampleData();
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "phone VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL"
                    +")";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSampleData() {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO contacts (firstName, lastName, phone, email) VALUES "
                    + "('John', 'Doe', '0423423423', 'johndoe@example.com'),"
                    + "('Jane', 'Doe', '0423423424', 'janedoe@example.com'),"
                    + "('Jay', 'Doe', '0423423425', 'jaydoe@example.com')";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addContact(Contact contact) {

    }

    @Override
    public void updateContact(Contact contact) {

    }

    @Override
    public void deleteContact(Contact contact) {

    }

    @Override
    public Contact getContact(int id) {
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return List.of();
    }
}
