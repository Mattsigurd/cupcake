package app.model;

import app.entities.User;

import java.sql.Date;
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

    public Orders createOrders(User user) {
        Date orderDate = new Date(System.currentTimeMillis());
        Orders orders = new Orders(user.getId(), orderDate, false, user.getId());
        return orders;
    }

    public List<Orderline> moveCartItemsToOrderlines(Orders orders) {
        List<Orderline> orderlines = new ArrayList<>();
        for (Orderline item : cartItems) {
            item.setOrder_id(orders.getId());
            orderlines.add(item);
        }
        cartItems.clear();
        return orderlines;
    }
}
