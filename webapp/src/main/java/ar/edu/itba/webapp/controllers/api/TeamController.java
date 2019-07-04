package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.webapp.model.DTOs.TeamDTO;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private LeagueService leagueService;

    @GET
    @Path("/team")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getTeam() {
        TeamDTO team = new TeamDTO(teamService.findByUserIdAndFetchPlayers(1L)); //userid

        return Response.ok(team).build();
    }

    @GET
    @Path("/teams")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getTeams() {
        League league = leagueService.findByUser(new User()).get(0); //current user
        List<Team> teams = teamService.findByLeague(league);
        List<TeamDTO> teamDTOS = new ArrayList<>();

        for (Team team : teams) {
            teamDTOS.add(new TeamDTO(team));
        }

        return Response.ok(teamDTOS).build();
    }


//    @POST
//    @Path("/team")
//    @Produces(value = { MediaType.APPLICATION_JSON, })
//    public Response chooseTeam(final TeamDTO team) {
//        return Response.created(uri).build();
//    }
}
