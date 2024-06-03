package com.example.addressbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    private static Connection instance = null;

    private SqliteConnection() {
        try {
            instance = DriverManager.getConnection("jdbc:sqlite:contacts.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if(instance == null){
            new SqliteConnection();
        }
        return instance;
    }
}
