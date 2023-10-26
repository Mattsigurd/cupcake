package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Cart;
import app.model.Orderline;
import app.model.Tops;
import app.persistence.*;
import io.javalin.http.Context;
import app.persistence.Calculator;


import java.util.ArrayList;
import java.util.List;



public class OrderController {




        public void deleteOrderlines(Orderline orderline, Context ctx) {

            User user = ctx.sessionAttribute("currentUser");
            Cart cart = ctx.sessionAttribute("cart");

            if (cart != null) {
                List<Orderline> newOrderline =cart.getCartItems();

                newOrderline.remove(orderline);

                ctx.sessionAttribute("cart", cart);
            }
        }

    public static void allTops(Context ctx, ConnectionPool connectionPool) {

        try {
            List<Tops> topsList = (List<Tops>) TopMapper.getAllTops(connectionPool);
            ctx.attribute("topsList", topsList);
            ctx.render("order.html");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

    }

    public static void allBottoms(Context ctx, ConnectionPool connectionPool) {

        try {
            List<Bottoms> bottomsList = (List<Bottoms>) BottomMapper.getAllBottoms(connectionPool);
            ctx.attribute("Bottoms", bottomsList);
            ctx.render("order.html");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createOrder(Context ctx) {
        User user = ctx.sessionAttribute("currentUser");


        int topId = Integer.parseInt(ctx.formParam("top_id"));
        int bottomId = Integer.parseInt(ctx.formParam("bottom_id"));
        int quantity = Integer.parseInt(ctx.formParam("quantity"));

        Orderline orderline = new Orderline(topId, bottomId, quantity);

        Cart cart = ctx.sessionAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            ctx.sessionAttribute("cart", cart);
        }
        cart.addToCart(orderline);
        ctx.redirect("/order");
    }

    /*public static void addtocart(Orderline orderline, Context ctx){

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
        //ctx.redirect("/cart");
    }*/
    public static void addtocart(Orderline orderline, Context ctx) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");
        Cart cart = ctx.sessionAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            ctx.sessionAttribute("cart", cart);
        }

        int calculatedPrice = Calculator.calculateTotalPrice(orderline.getTop_id(), orderline.getBottom_id(), orderline.getQuantity(), ConnectionPool.getInstance());


        orderline.setTotalPrice(calculatedPrice);

        cart.addToCart(orderline);

        //ctx.redirect("/cart");

    }
}

