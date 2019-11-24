package at.htl.jeopardized.category;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryEndpoint {
    @Inject
    CategoryDao categoryDao;

    @GET
    public Response getCategories(){
        return Response.ok(categoryDao.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategory(@PathParam("id")long id){
        return Response.ok(categoryDao.getById(id)).build();
    }

    @GET
    @Path("/averageValue")
    public Response getAverageValue(@QueryParam("category") @DefaultValue("-1") long category){
        return Response.ok(categoryDao.getAverageValue(category)).build();
    }
}
