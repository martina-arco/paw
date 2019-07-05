package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.FormationService;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Formation;
import ar.edu.itba.webapp.model.DTOs.FormationDTO;
import ar.edu.itba.webapp.model.DTOs.TeamDTO;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("teams")
@Component
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private FormationService formationService;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getTeams() {
        League league = leagueService.findByUser(new User()).get(0); //TODO current user. Esto tira indexoutofbounds
        List<Team> teams = teamService.findByLeague(league);
        List<TeamDTO> teamDTOS = new ArrayList<>();

        for (Team team : teams) {
            teamDTOS.add(new TeamDTO(team));
        }

        return Response.ok(teamDTOS).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getById(@PathParam("id") final int id) {
        final Team team = teamService.findByIdAndFetchPlayers(id);
        if (team != null) {
            return Response.ok(new TeamDTO(team)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/formation")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getFormation(@PathParam("id") final int id) {
        Team team = teamService.findById(id);
        Formation formation = formationService.findById(team.getFormationId());
        return Response.ok(new FormationDTO(formation)).build();
    }

    @GET
    @Path("/current")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getTeam() {
        Team team = teamService.findByUserIdAndFetchPlayers(1L); //TODO userid
        if (team != null) {
            return Response.ok(new TeamDTO(team)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


//    @POST
//    @Path("/team")
//    @Produces(value = { MediaType.APPLICATION_JSON, })
//    public Response chooseTeam(final TeamDTO team) {
//        return Response.created(uri).build();
//    }
}
