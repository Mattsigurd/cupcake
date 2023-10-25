package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.model.Bottoms;
import app.model.Tops;
import app.persistence.BottomMapper;
import app.persistence.ConnectionPool;
import app.persistence.TopMapper;
import app.persistence.UserMapper;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class UserController
{
    public static void login(Context ctx, ConnectionPool connectionPool)
    {
        Map<Integer, Tops> topsMap;
        Map<Integer, Bottoms> bottomsMap;

        String name = ctx.formParam("username");
        String password = ctx.formParam("password");
        try
        {
            User user = UserMapper.login(name, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            topsMap = (HashMap<Integer, Tops>) ctx.sessionAttribute("topsMap");
            if (topsMap == null)
            {

                // Hent alle tops fra DB og gem i Hashmap
                topsMap = TopMapper.getAllTops(connectionPool);
                ctx.sessionAttribute("topsMap", topsMap);
            }
            bottomsMap = (HashMap<Integer,  Bottoms>) ctx.sessionAttribute("bottomsMap");
            if (bottomsMap == null)
            {
                // Hent alle bottoms fra DB og gem i Hashmap
                bottomsMap = BottomMapper.getAllBottoms(connectionPool);
                ctx.sessionAttribute("bottomsMap", bottomsMap);

            }
            ctx.attribute("message", "Du er nu logget ind");
            ctx.render("index.html");
        }
        catch (DatabaseException e)
        {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    public static void createuser(Context ctx, ConnectionPool connectionPool)
    {
        String name = ctx.formParam("email");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");
        String role = ctx.formParam("role");

        // Validering af passwords - at de to matcher
        if (password1.equals(password2))
        {
            try
            {
                UserMapper.createuser(name, password1, connectionPool);
                ctx.attribute("message", "Du er nu oprette. Log på for at komme i gang.");
                ctx.render("index.html");

            }
            catch (DatabaseException e)
            {
                ctx.attribute("message", e.getMessage());
                ctx.render("createuser.html");
            }
        } else
        {
            ctx.attribute("message", "Dine password matcher ikke!");
            ctx.render("createuser.html");
        }

    }

    public static void logout(Context ctx)
    {
        // Invalidate session
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }
}
