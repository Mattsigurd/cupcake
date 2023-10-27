package app.persistence;

import app.exceptions.DatabaseException;
import app.model.Cart;

public class EndPrice {

    public static boolean canCompleteOrder(int user_id, ConnectionPool connectionPool) throws DatabaseException {
        int remainingBalance = OrderMapper.registerBalance(user_id, connectionPool);
        Cart cart=new Cart();
        if(remainingBalance >= cart.getTotalPrice()){
            return true;
        }else{
            return false;
    }
}
    
}
