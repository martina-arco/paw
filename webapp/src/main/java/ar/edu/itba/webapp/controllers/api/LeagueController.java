package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.LeagueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("league")
@Component
public class LeagueController extends Controller {

    @Autowired
    private MatchService matchService;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private TeamService teamService;


    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getLeague() {
        User user = loggedUser();
        Team team = teamService.findByUserId(user.getId());
        League league = leagueService.findByUser(user).get(0);

        List<Match> upcomingMatches = matchService.getUpcomingMatches(team, user.getCurrentDay());
        List<Map.Entry<String,Integer>> teams = leagueService.getTeamPointsName(league, user.getCurrentDay());
        Integer matchesToPlay = leagueService.matchesToPlay(user, team);
        Integer matchesPlayed = leagueService.matchesPlayed(user, team);
        List<Match> matches = matchService.findByTeamId(team.getId());
        Set<Integer> years = new HashSet<>();

        for (Match match : matches) {
            Date date = match.getDay();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            years.add(calendar.get(Calendar.YEAR));
        }

        int seasonAmount = years.size();

        return Response.ok(new LeagueDTO(league, matchesToPlay, matchesPlayed, teams, upcomingMatches, seasonAmount)).build();
    }
}