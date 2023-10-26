package app.persistence;

import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Orderline;
import app.model.Tops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private List<Orderline> getAllOrderLines(ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> orderlineList = new ArrayList<>();
        String sql = "SELECT orderline.id, orderline.order_id, orderline.quantity, tops.top, tops.top_id, " +
                " tops.top_price as top_price, bottoms.bottom, bottoms.bottom_id, bottoms.bottom_price as bottom_price, " +
                "orderline.total_price FROM orderline JOIN tops ON orderline.top_id = tops.top_id JOIN bottoms ON orderline.bottom_id = bottoms.bottom_id";

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

    public static int findOrderIdByUserId(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT order_id FROM user_orders WHERE user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("order_id");
                    } else {
                        throw new DatabaseException("Ingen order fundet med brugeren " + userId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Kan ikke connecte til DB i findOrderIdByUserId: " + e.getMessage());
        }
    }

    public static Orderline insertOrderline(Orderline orderline, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO orderline (id, order_id, quantity, top_id, bottom_id, total_price) VALUES (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderline.getId());
                ps.setInt(2, orderline.getOrder_id());
                ps.setInt(3, orderline.getQuantity());
                ps.setInt(4, orderline.getTop_id());
                ps.setInt(5, orderline.getBottom_id());
                ps.setInt(6, orderline.getTotalPrice());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new DatabaseException("Fejl i insertOrderline med SQL query: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i insertOrderline med forbindelse til database: " + e.getMessage());
        }
        return orderline;
    }
}
