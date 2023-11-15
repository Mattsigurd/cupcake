package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Cart;
import app.model.Orderline;
import app.model.Tops;
import io.javalin.http.Context;

import java.util.Map;

public class CartController {

    public static void addtocart(Context ctx) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");
        Cart cart = ctx.sessionAttribute("cart");
        Map<Integer, Tops> topsMap = (Map<Integer, Tops>) ctx.sessionAttribute("topsMap");
        Map<Integer, Bottoms> bottomsMap = (Map<Integer, Bottoms>) ctx.sessionAttribute("bottomsMap");

        int topId = Integer.parseInt(ctx.formParam("top_id"));
        int bottomId = Integer.parseInt(ctx.formParam("bottom_id"));
        int quantity = Integer.parseInt(ctx.formParam("quantity"));

        if (cart == null) {
            cart = new Cart();
            ctx.sessionAttribute("cart", cart);
        }

        Orderline orderline = new Orderline(quantity, topsMap.get(topId), bottomsMap.get(bottomId),  topId, bottomId);

        cart.addToCart(orderline);
        ctx.sessionAttribute("cart", cart);

        ctx.render("order.html");

    }
}
