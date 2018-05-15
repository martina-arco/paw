package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.League;
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

    @RequestMapping("/league")
    public ModelAndView league() {
        ModelAndView mav = new ModelAndView();

//        User user = loggedUser();
//        List<String> upcomingMatches = matchService.getUpcomingMatches(user.getTeam(), user.getCurrentDay());
//        Map<String, Integer> teams = leagueService.getTeamPoints(user.getTeam().getLeague(), user.getCurrentDay());
//
//        mav.addObject("upcomingMatches", upcomingMatches);
//        mav.addObject("teams", teams);
        return mav;
    }
}
