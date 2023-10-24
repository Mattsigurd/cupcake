package app.persistence;

import app.model.Bottoms;
import app.model.Tops;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<Tops> getAllTops(ConnectionPool connectionPool) throws DatabaseException {
        List<Tops> topsList = new ArrayList<>();
        String sql = "select * from the_tops";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int top_id = rs.getInt("top_id");
                    String top = rs.getString("top");
                    int price = rs.getInt("price");
                    topsList.add(new Tops(top_id, top, price));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in GroupFMapper with drinks " + e);
        }
        return topsList;
    }

    public static List<Bottoms> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {
        List<Bottoms> bottomsList = new ArrayList<>();
        String sql = "select * from the_bottoms";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int bottom_id = rs.getInt("bottom_id");
                    String bottom = rs.getString("bottom");
                    int price = rs.getInt("price");
                    bottomsList.add(new Bottoms(bottom_id, bottom, price));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in GroupFMapper with drinks " + e);
        }
        return bottomsList;
    }
}
