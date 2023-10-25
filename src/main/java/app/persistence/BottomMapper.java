package app.persistence;

import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Tops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BottomMapper {


    public static Map<Integer, Bottoms> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {

        Map<Integer, Bottoms> bottomsMap = new HashMap<>();

        String sql= "SELECT * from bottoms";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int bottom_id = rs.getInt("bottom_id");
                    String bottom = rs.getString("bottom");
                    int price = rs.getInt("bottom_price");
                    Bottoms bottoms = new Bottoms(bottom_id, bottom, price);
                    bottomsMap.put(bottom_id, bottoms);

                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in BottomMapper" + e);
        }
        return bottomsMap;
    }
}
