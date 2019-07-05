package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.*;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("matches")
@Component
public class MatchController extends Controller {

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private SimulationService simulationService;

//  TODO: hacerlo para toda la simulacion
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getMatches() {
        User user = new User(); //logged user
        League league = leagueService.findByUser(user).get(0);
        List<Match> matches = leagueService.findMatchesForDate(league, user.getCurrentDay());

        simulationService.simulateFixture(user.getId(), matches);

        return Response.ok(matches).build();
    }

    @GET
    @Path("/current")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getMatch() {
        User user = loggedUser();
        League league = leagueService.findByUser(user).get(0);
        List<Match> matches = leagueService.findMatchesForDate(league, user.getCurrentDay());
        Match userMatch = matchService.getUserMatch(matches, user);

        return Response.ok(new MatchDTO(userMatch)).build();
    }

    @GET
    @Path("/upcoming")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getUpcomingMatches() {
        User user = loggedUser();

        Team team = teamService.findByUserIdAndFetchPlayers(user.getId());
        List<Match> matches = matchService.getUpcomingMatches(team, user.getCurrentDay());
        List<MatchDTO> matchDTOS = new ArrayList<>();

        for (Match match : matches) {
            matchDTOS.add(new MatchDTO(match));
        }

        return Response.ok(matchDTOS).build();
    }

    @GET
    @Path("/next")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getUpcomingMatch() {
        User user = loggedUser();

        Team team = teamService.findByUserIdAndFetchPlayers(user.getId());
        Match upcomingMatch = matchService.getUpcomingMatches(team, user.getCurrentDay()).get(0);

        return Response.ok(new MatchDTO(upcomingMatch)).build();
    }

}
