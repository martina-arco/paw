package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.*;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.MatchDTO;
import ar.edu.itba.webapp.model.DTOs.MatchShortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getUpcomingMatches() {
        User user = loggedUser();

        Team team = teamService.findByUserIdAndFetchPlayers(user.getId());
        List<Match> matches = matchService.getUpcomingMatches(team, user.getCurrentDay());
        List<MatchShortDTO> matchDTOS = new ArrayList<>();

        for (Match match : matches) {
            matchDTOS.add(new MatchShortDTO(match));
        }

        return Response.ok(matchDTOS).build();
    }

    @GET
    @Path("/lastUserPlayed")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getMatch() {
        User user = loggedUser();
        Match userMatch = matchService.getUserMatch(matchService.getMatchesPlayed(user), user);

        return Response.ok(new MatchDTO(userMatch)).build();
    }

    @GET
    @Path("/lastPlayed")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getMatches() {
        User user = loggedUser();
        return Response.ok(matchService.getMatchesPlayed(user).parallelStream().map(MatchDTO::new)
                .collect(Collectors.toList())).build();
    }

    @GET
    @Path("/next")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getUpcomingMatch() {
        User user = loggedUser();

        Team team = teamService.findByUserIdAndFetchPlayers(user.getId());
        if (team != null) {
            Match upcomingMatch = matchService.getUpcomingMatches(team, user.getCurrentDay()).get(0);

            return Response.ok(new MatchShortDTO(upcomingMatch)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/play")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response simulateMatches() {
        User user = loggedUser();
        League league = leagueService.findByUser(user).get(0);
        List<Match> matches = leagueService.findMatchesForDate(league, user.getCurrentDay());
        Match userMatch = matchService.getUserMatch(matches, user);

        if(!simulationService.started(userMatch)){
            simulationService.simulateFixture(user.getId(), matches);
        }

        return Response.ok(matches.parallelStream().map(MatchDTO::new).collect(Collectors.toList())).build();
    }

}
