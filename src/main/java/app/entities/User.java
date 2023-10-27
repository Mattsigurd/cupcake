package app.entities;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User
{
    private int id;
    private String email;
    private String password;
    private String role;
    private double balance;

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
   public User(int id, String email, String password, String role, double balance)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role= role;
        this.balance= balance;
    }

    public User(int id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRole() {
        return role;
    }
    public double getBalance() {
        return balance;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                '}';
    }


    /*
    public static String findOrderIdByRole(String userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT order_id FROM orders WHERE user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, findOrderIdByRole());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("order_id");
                    } else {
                        throw new DatabaseException("Ingen order fundet med brugeren " + userId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Kan ikke connecte til DB i findOrderIdByRole: " + e.getMessage());
        }
    }

     */
}
