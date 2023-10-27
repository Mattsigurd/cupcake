package app.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrderContainer {
    private Connection connection;

    public OrderContainer(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


////  lave en if statement på User der kan hente dataen herfra som Administrator