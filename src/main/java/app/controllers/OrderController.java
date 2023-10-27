package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Cart;
import app.model.Orderline;
import app.model.Tops;
import app.persistence.*;
import io.javalin.http.Context;


import java.util.ArrayList;
import java.util.List;

import static app.persistence.OrderMapper.findOrderIdByUserId;


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
            List<Tops> topsList = new ArrayList<>(TopMapper.getAllTops(connectionPool).values());
            ctx.attribute("topsList", topsList);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

    }

    public static void allBottoms(Context ctx, ConnectionPool connectionPool) {

        try {
            List<Bottoms> bottomsList =  new ArrayList<>( BottomMapper.getAllBottoms(connectionPool).values());
            ctx.attribute("Bottoms", bottomsList);

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }



    public static void createOrder(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");
        Cart cart = ctx.sessionAttribute("cart");

        if (cart != null) {
            List<Orderline> cartItems = cart.getCartItems();
            int userId = user.getId();

            try {
                int order_id = findOrderIdByUserId(userId, connectionPool);

                for (Orderline orderline : cartItems) {
                    orderline.setOrder_id(order_id);
                    Orderline insertedOrderline = OrderMapper.insertOrderline(orderline, connectionPool);
                }

                cartItems.clear();
                ctx.sessionAttribute("cart", cart);

                ctx.redirect("/payment");
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void allOrderline(Context ctx, ConnectionPool connectionPool) throws DatabaseException{

        try {
            List<Orderline> orderlineArrayList=  new ArrayList<>(OrderMapper.getAllOrderLines(connectionPool));
            ctx.attribute("Orderline", orderlineArrayList);

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}