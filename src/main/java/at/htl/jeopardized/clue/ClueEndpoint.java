package at.htl.jeopardized.clue;

import at.htl.jeopardized.category.CategoryDao;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

@Path("clues")
@Produces(MediaType.APPLICATION_JSON)
public class ClueEndpoint {
    @Inject
    ClueDao clueDao;

    @GET
    @Timed(name = "clue_of_category",unit = MetricUnits.MILLISECONDS)
    public Response getClues(@QueryParam("offset")@DefaultValue("0")int offset,@QueryParam("limit")@DefaultValue("20")int limit){
        return Response.ok(clueDao.getAll(offset,limit)).build();
    }

    @GET
    @Path("/{id}")
    public Response getClueById(@PathParam("id")long id){
        return Response.ok(clueDao.getById(id)).build();
    }

}
