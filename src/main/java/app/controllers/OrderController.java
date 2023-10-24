package app.controllers;

import app.entities.User;
import app.model.Bottoms;
import app.model.Cart;
import app.model.Orderline;
import app.model.Tops;
import io.javalin.http.Context;

public class OrderController {


    public void addtocart(Orderline orderline, Context ctx){

        User user = ctx.sessionAttribute("currentUser");
        Cart cart = ctx.sessionAttribute("cart");
        int topId = Integer.parseInt(ctx.formParam("top_id"));
        int bottomId = Integer.parseInt(ctx.formParam("bottom_id"));
        int quantity = Integer.parseInt(ctx.formParam("quantity"));

        Orderline orderLine = new Orderline(topId, bottomId, quantity);

        if (cart == null) {
            cart = new Cart();
            ctx.sessionAttribute("cart", cart);
        }

        cart.addToCart(orderLine);
        }


    }
    
