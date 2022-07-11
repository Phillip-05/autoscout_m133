package dev.phill.autoscout.service;

import dev.phill.autoscout.data.DataHandler;
import dev.phill.autoscout.model.User;
import dev.phill.autoscout.util.AES256;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;


/**
 * services for authentication and authorization of users
 */
@Path("user")
public class UserService {

    /**
     * login-service
     *
     * @param username the username
     * @param password the password
     * @return Response
     */
    @PermitAll
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        User loggedInUser = DataHandler.readUser(username, password);
        String userRole = loggedInUser.getRole();
        String token = username + ";" + userRole;

        NewCookie cookie = new NewCookie(
                "logincookie",
                AES256.encrypt(token),
                "/",
                "",
                "Auth-Token",
                600,
                false
        );

        NewCookie facookie = new NewCookie(
                "facookie",
                "test",
                "/",
                "",
                "Auth-Token",
                600,
                false
        );

        return Response.ok().cookie(cookie,facookie)
                .entity(loggedInUser.getUsername() + " successfully logged in!").build();
    }

    @PermitAll
    @Path("logout")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoutUser() {

        NewCookie cookie = new NewCookie(
                "logincookie",
                "",
                "/",
                "",
                "Auth-Token",
                1,
                false
        );



        return Response.ok().cookie(cookie).build();

    }

    @PermitAll
    @Path("2fa")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkWord(
            @CookieParam("facookie") Cookie facookie,
            @FormParam("secret") String secret
    ) {
        int httpStatus = 200;

        String value = facookie.getValue();

        if (value == null || !value.equals(secret)){
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(null)
                .build();
    }
}