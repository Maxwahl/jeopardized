package at.htl.jeopardized.business;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
public interface JeopardyService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/categories")
    JsonArray getCategories(@QueryParam("count") int count, @QueryParam("offset") int offset);
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/api/clues")
    JsonArray getClues(@QueryParam("category") int category,@QueryParam("offset") int offset);
}
