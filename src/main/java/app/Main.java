package app;

import app.controllers.OrderController;
import app.controllers.UserController;
import app.model.Cart;
import app.persistence.ConnectionPool;
import config.ThymeleafConfig;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "cupcake";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);



    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            JavalinThymeleaf.init(ThymeleafConfig.templateEngine());
        }).start(7090);

        // Routing

        app.get("/", ctx -> ctx.render("index.html"));
        app.post("/login", ctx -> UserController.login(ctx, connectionPool));
        app.get("/createuser", ctx -> ctx.render("createuser.html"));
        app.post("/createuser",ctx -> UserController.createuser(ctx, connectionPool ));
        app.get("/logout", ctx -> UserController.logout(ctx));
        app.get("/order", ctx -> {
            OrderController.allTops(ctx, connectionPool);
            OrderController.allBottoms(ctx, connectionPool);
        });
        app.post("/cart", ctx-> OrderController.createOrder(ctx));
    }
}