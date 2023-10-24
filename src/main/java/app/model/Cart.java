package app.model;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Orderline> cart(ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> cartList = new ArrayList<>();
        String sql = "SELECT orderline.id, orderline.order_id, orderline.quantity, the_tops.top, the_bottoms.bottom, " +
                "orderline.total_price FROM orderline JOIN the_tops ON orderline.top_id = the_tops.top_id JOIN the_bottoms ON orderline.bottom_id = the_bottoms.bottom_id;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int order_id= rs.getInt("order_id");
                    int quantity = rs.getInt("quantity");
                    String tops = rs.getString("top_id");
                    String bottoms = rs.getString("bottom_id");
                    int total_price = rs.getInt("total_price");
                    cartList.add(new Orderline(id, order_id, quantity, tops, bottoms, total_price));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in GroupFMapper with drinks " + e);
        }
        return cartList;
    }


    public void addToCart(Orderline orderline){



    }
}
