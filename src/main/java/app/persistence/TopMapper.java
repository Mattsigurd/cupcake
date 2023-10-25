package app.persistence;

import app.exceptions.DatabaseException;
import app.model.Tops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopMapper {

    public static Map<Integer, Tops> getAllTops(ConnectionPool connectionPool) throws DatabaseException {

        Map<Integer, Tops> topsMap = new HashMap<>();

        String sql= "SELECT * from tops";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int top_id = rs.getInt("top_id");
                    String top = rs.getString("top");
                    int price = rs.getInt("top_price");
                    Tops tops = new Tops(top_id, top, price);
                    topsMap.put(top_id, tops);

                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in TopMapper" + e);
        }
        return topsMap;
    }
}
