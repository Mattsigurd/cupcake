package app.persistence;

import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Orderline;
import app.model.Tops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapper {

    private List<Orderline> getAllOrderLines(ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> cartList = new ArrayList<>();
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
                    cartList.add(new Orderline(id, order_id, quantity,
                            new Tops(top_id, top, price),
                            new Bottoms(bottom_id, bottom, bottom_price),
                            total_price));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in OrderMapper " + e);
        }
        return cartList;
    }


}
