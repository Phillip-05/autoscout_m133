package dev.phill.autoscout.service;


import ch.bzz.bookshelf.data.DataHandler;
import ch.bzz.bookshelf.model.Book;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * test service
 */
    @Path("book")
    public class Bookservice {

        @GET
        @Path("list")
        @Produces(MediaType.APPLICATION_JSON)
        public Response listBooks() {
            List<Book> booklist = DataHandler.getInstance().readAllBooks();
            return Response
                    .status(200)
                    .entity(booklist)
                    .build();
        }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readbook(
        @QueryParam("uuid") String bookUUID

    ){
        Book book = DataHandler.getInstance().readBookByUUID(bookUUID);
        return Response
                .status(200)
                .entity(book)
                .build();
    }
}