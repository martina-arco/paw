package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.itba.model.Event;
import ar.edu.itba.model.utils.MatchStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@org.springframework.stereotype.Controller
public class MatchController extends Controller{

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private SimulationService simulationService;

    @RequestMapping("/match")
    public ModelAndView match() {
        ModelAndView mav = new ModelAndView("match");

        User user = loggedUser();
        League league = leagueService.findByUser(user).get(0);
        List<Match> matches = leagueService.findMatchesForDate(league, user.getCurrentDay());

        matchService.setTeamsAndFormations(matches);
        stadiumService.setStadium(matches);
        simulationService.simulateFixture(user.getId(), matches);

        mav.addObject("matches", matches);

        return mav;
    }

    @RequestMapping(value = "/data", produces = "application/json")
    @ResponseBody
    public Object json() {
        return simulationService.getStatus(loggedUser().getId());
    }

    private ModelAndView matchEnds() {
        return new ModelAndView("redirect:matchEnd");
    }

    @RequestMapping("/matchEnd")
    public ModelAndView matchEnd() {
        ModelAndView mav = new ModelAndView("matchEnd");
        User user = loggedUser();

        List<Match> matches = simulationService.getMatches(user.getId());
        matchService.saveMatches(matches, user);
        userService.advanceDate(user);

        Match userMatch = matchService.getUserMatch(matches, user);

        mav.addObject("matches", matches);
        mav.addObject("match", userMatch);
        mav.addObject("stadium", stadiumService.findByTeam(userMatch.getHome()));

        return mav;
    }
}
