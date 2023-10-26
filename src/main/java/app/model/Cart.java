package app.model;

import app.persistence.Calculator;

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
    public double getTotalPrice(){

        double sum = 0;

        for (Orderline cartItem : cartItems) {
            sum = sum + cartItem.getQuantity() * (cartItem.getTops().getTop_price() + cartItem.getBottoms().getBottom_price());
        }

        return sum;
    }
}
