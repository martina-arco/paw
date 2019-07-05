package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.FormationDTO;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("formation")
@Component
public class FormationController extends Controller {

    @Autowired
    private TeamService teamService;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getFormation() {
        Team team = teamService.findByUserIdAndFetchPlayersAndFormation(loggedUser().getId());
        Formation formation = team.getFormation();
        return Response.ok(new FormationDTO(formation)).build();
    }

//  TODO: save formation

//    @POST
//    @Path("/")
//    @Produces(value = { MediaType.APPLICATION_JSON, })
//    public Response saveFormation(final Formation formation) {
//        return Response.created(uri).build();
//    }
}
