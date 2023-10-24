package app.controllers;

import app.entities.User;
import app.model.Cart;
import app.model.Orderline;
import io.javalin.http.Context;

public class OrderController {


    public void addtocart(Orderline orderline, Context ctx){

        User user = ctx.sessionAttribute("currentUser");
        String cartList = ctx.formParam("addToCart");
        Cart cart= Cart.addToCart();



    }

}
