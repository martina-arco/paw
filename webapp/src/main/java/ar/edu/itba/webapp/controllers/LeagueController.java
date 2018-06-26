package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class LeagueController extends Controller {

    @Autowired
    private MatchService matchService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TeamService teamService;

    @RequestMapping("/league")
    public ModelAndView league() {
        ModelAndView mav = new ModelAndView();

        User user = loggedUser();
        Team team = teamService.findByUserId(user.getId());
        League league = leagueService.findByUser(loggedUser()).get(0);

        List<Match> upcomingMatches = matchService.getUpcomingMatches(team, user.getCurrentDay());
        List<Map.Entry<String,Integer>> teams = leagueService.getTeamPointsName(league, user.getCurrentDay());


        mav.addObject("upcomingMatches", upcomingMatches);
        mav.addObject("teams", teams);
        return mav;
    }
}
