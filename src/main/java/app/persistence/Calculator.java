package app.persistence;

import app.exceptions.DatabaseException;

import static app.persistence.OrderMapper.getBottomPrice;
import static app.persistence.OrderMapper.getTopPrice;

public class Calculator {

    public static int calculateTotalPrice(int top_id, int bottom_id, int quantity, ConnectionPool connectionPool) throws DatabaseException {
        int topPrice = getTopPrice(top_id, connectionPool);
        int bottomPrice = getBottomPrice(bottom_id, connectionPool);

        int totalPrice = quantity * (topPrice + bottomPrice);

        return totalPrice;
    }

}
