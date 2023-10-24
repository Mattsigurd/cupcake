package app.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

   private List<Orderline> cartItems = new ArrayList<>();


    public void addToCart(Orderline orderline){
        cartItems.add(orderline);
    }

    public List<Orderline> getCartItems() {
        return cartItems;
    }

    public void deleteCartItems(Orderline orderline){

        cartItems.remove(orderline);
    }
}
