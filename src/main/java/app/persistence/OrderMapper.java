package app.persistence;

import app.controllers.OrderController;
import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Orderline;
import app.model.Orders;
import app.model.Tops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static List<Orderline> getAllOrderLines(ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> orderlineList = new ArrayList<>();
        String sql = "SELECT orderline.id, orderline.order_id, orderline.quantity, tops.top, tops.top_id, " +
                " tops.top_price as top_price, bottoms.bottom, bottoms.bottom_id, bottoms.bottom_price as bottom_price, " +
                "orderline.total_price FROM orderline JOIN tops ON orderline.top_id = tops.top_id JOIN bottoms ON orderline.bottom_id = bottoms.bottom_id where orderline.user_id = :currentUser";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int order_id= rs.getInt("order_id");
                    int quantity = rs.getInt("quantity");
                    int top_id = rs.getInt("top_id");
                    String top = rs.getString("top");
                    int price = rs.getInt("top_price");
                    int bottom_id= rs.getInt("bottom_id");
                    String bottom = rs.getString("bottom");
                    int bottom_price = rs.getInt("bottom_price");
                    int total_price = rs.getInt("total_price");
                    orderlineList.add(new Orderline(id, order_id, quantity,
                            new Tops(top_id, top, price),
                            new Bottoms(bottom_id, bottom, bottom_price),
                            total_price));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in OrderMapper " + e);
        }
        return orderlineList;
    }

    public static int getTopPrice(int top_id, ConnectionPool connectionPool) throws DatabaseException {
        int topPrice = 0; // Default value or error handling

        String sql = "SELECT top_price FROM tops WHERE top_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, top_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    topPrice = rs.getInt("top_price");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error in OrderMapper (getTopPrice): " + e.getMessage());
        }

        return topPrice;
    }


    public static int getBottomPrice(int bottom_id, ConnectionPool connectionPool) throws DatabaseException {
        int bottomPrice = 0;

        String sql = "SELECT bottom_price FROM bottoms WHERE bottom_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bottom_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bottomPrice = rs.getInt("bottom_price");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i OrderMapper (getBottomPrice): " + e.getMessage());
        }

        return bottomPrice;
    }


    public static int findUserIdByCurrentUser(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT user_id FROM orders WHERE id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("user_id");
                    } else {
                        throw new DatabaseException("Ingen order fundet med brugeren " + id);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Kan ikke connecte til DB i findUserIdByCurrentUser: " + e.getMessage());
        }
    }


    public static int findOrderIdByUserId(int user_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT orders.id FROM orders WHERE user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id");
                    } else {
                        throw new DatabaseException("Ingen order fundet med brugeren " + user_id);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Kan ikke connecte til DB i findOrderIdByUserId: " + e.getMessage());
        }
    }

    public static Orders insertOrders(Orders orders, List<Orderline> orderlines, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "INSERT INTO orders (date, paid, user_id) VALUES (?,?,?)";
        int newOrderId = 0;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDate(1, orders.getDate());
                ps.setBoolean(2, orders.isPaid());
                ps.setInt(3, orders.getUser_id());
                int rowsAffected = ps.executeUpdate();

                // Hent det nye id for ordren
                if (rowsAffected == 1)
                {
                    ResultSet keys = ps.getGeneratedKeys();
                    keys.next();
                    newOrderId = keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i insertOrders med SQL query: " + e.getMessage());
    }
        for (Orderline orderline : orderlines) {
            insertOrderline(orderline, newOrderId, connectionPool);
        }

        return orders;
    }

    public static Orderline insertOrderline(Orderline orderline, int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO orderline (order_id, quantity, top_id, bottom_id, total_price) VALUES (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.setInt(2, orderline.getQuantity());
                ps.setInt(3, orderline.getTop_id());
                ps.setInt(4, orderline.getBottom_id());
                ps.setDouble(5, orderline.getTotalPrice());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new DatabaseException("Fejl i insertOrderline med SQL query: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i insertOrderline med forbindelse til database: " + e.getMessage());
        }

        return orderline;
    }

    public static int registerBalance(int user_id , ConnectionPool connectionPool) throws DatabaseException {
        int remainingBalance = 0;

        String sql = "SELECT \"user\".balance WHERE \"user\".id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    remainingBalance = rs.getInt("balance");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error in OrderMapper (getBalance): " + e.getMessage());
        }

        return remainingBalance;
    }
}
