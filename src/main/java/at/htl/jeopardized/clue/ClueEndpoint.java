package at.htl.jeopardized.clue;

import at.htl.jeopardized.category.CategoryDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("clues")
@Produces(MediaType.APPLICATION_JSON)
public class ClueEndpoint {
    @Inject
    ClueDao clueDao;

    @GET
    public Response getCategories(@QueryParam("offset")@DefaultValue("0")int offset,@QueryParam("limit")@DefaultValue("20")int limit){
        return Response.ok(clueDao.getAll(offset,limit)).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategory(@PathParam("id")long id){
        return Response.ok(clueDao.getById(id)).build();
    }

}
