package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.FormationService;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Formation;
import ar.edu.itba.webapp.controllers.Controller;
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
import java.util.LinkedList;
import java.util.List;

@Path("teams")
@Component
public class TeamController extends Controller {

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
        List<League> leagues = leagueService.findByUser(loggedUser());
        if (leagues.isEmpty()) {
            return Response.noContent().build();
        } else {
            League league = leagues.get(0); //TODO siempre la 0 ?
            List<Team> teams = teamService.findByLeagueAndFetchPlayers(league);
            List<TeamDTO> teamDTOS = new LinkedList<>();

            for (Team team : teams) {
                teamDTOS.add(new TeamDTO(team));
            }

            return Response.ok(teamDTOS).build();

        }
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
        Team team = teamService.findByIdAndFetchFormation(id);
        if (team != null) {
            Formation formation = team.getFormation();
            if (formation != null) {
                return Response.ok(new FormationDTO(formation)).build();
            }
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/current")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getTeam() {
        Team team = teamService.findByUserIdAndFetchPlayers(loggedUser().getId());
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
