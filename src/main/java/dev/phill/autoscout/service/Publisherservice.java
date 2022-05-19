package dev.phill.autoscout.service;

import ch.bzz.bookshelf.data.DataHandler;
import ch.bzz.bookshelf.model.Publisher;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("publisher")
public class Publisherservice {

    /**
     * reads a list of all publishers
     * @return  publishers as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPublishers() {
        List<Publisher> publisherList = DataHandler.getInstance().readAllPublishers();
        return Response
                .status(200)
                .entity(publisherList)
                .build();
    }

    /**
     * reads a publisher identified by the uuid
     * @param publisherUUID
     * @return publisher
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPublisher(
            @QueryParam("uuid") String publisherUUID
    ) {
        Publisher publisher = DataHandler.getInstance().readPublisherByUUID(publisherUUID);
        return Response
                .status(200)
                .entity(publisher)
                .build();
    }
}
