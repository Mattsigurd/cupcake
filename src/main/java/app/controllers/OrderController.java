package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.model.*;
import app.persistence.*;
import io.javalin.http.Context;


import java.sql.Date;
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

    public static void createOrders(Context ctx, ConnectionPool connectionPool) {
        Cart cart = ctx.sessionAttribute("cart");
        if (cart != null) {
            ctx.attribute("orderlineArrayList", cart.getCartItems());
            ctx.render("payment.html");
        }
    }

    public static void allOrderline(Context ctx, ConnectionPool connectionPool) throws DatabaseException
    {
        User user = (User) ctx.sessionAttribute("currentUser");
        Cart cart = (Cart) ctx.sessionAttribute("cart");

        Orders orders = new Orders(0, new Date(System.currentTimeMillis()), true, user.getId()  );
        try {
            orders = OrderMapper.insertOrders(orders, cart.getCartItems(), connectionPool );
            ctx.render("order.html");

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}