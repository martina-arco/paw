package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.model.Event;
import ar.edu.itba.model.utils.MatchStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class MatchController extends Controller{

    @Autowired
    private MatchService matchService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private SimulationService simulationService;

    @RequestMapping("/match")
    public ModelAndView match() {
        ModelAndView mav = new ModelAndView("match");
        List<Match> matches = leagueService.findByUser(loggedUser()).get(0).getFixture().get(loggedUser().getCurrentDay());
        mav.addObject("matches", matches);
        simulationService.simulateFixture(loggedUser().getId(),matches);
        simulationService.start(loggedUser().getId());  //No termina hasta que termina la simulacion. TODO @lemery
        return mav;
    }

    @RequestMapping(value = "/data", produces = "application/json")
    @ResponseBody
    public Object json() {
        Map<Long, MatchStatus> map = new HashMap<>();
        map = simulationService.getStatus(loggedUser().getId());
        /*
        List<Event> l1 = new ArrayList<>();
        List<Event> l2 = new ArrayList<>();
        l1.add(new Event(1,null, Event.Type.YELLOW_CARD, 1));
        l1.add(new Event(2,null, Event.Type.RED_CARD, 1));
        l1.add(new Event(2,null, Event.Type.SCORE, 1));
        l2.add(new Event(3,null, Event.Type.RED_CARD, 1));
        l2.add(new Event(4,null, Event.Type.ASSIST, 1));

        map.put(1, new MatchStatus(2,0, (int) Math.round( Math.random() * 90), l1));
        map.put(2, new MatchStatus(0,0, (int) Math.round( Math.random() * 90), l2));
        */
        return map;
    }

    private ModelAndView matchEnds() {
        return new ModelAndView("redirect:matchEnd");
    }

    @RequestMapping("/matchEnd")
    public ModelAndView matchEnd() {
        ModelAndView mav = new ModelAndView("matchEnd");

//        Map<String, Integer> homeScores = new HashMap<>();
//        Map<String, Integer> awayScores = new HashMap<>();
//
//        List<Match> matches = leagueService.findByUser(loggedUser()).get(0).getFixture().get(loggedUser().getCurrentDay());
//        Match userMatch = matchService.getUserMatch(matches, loggedUser().getTeam());
//
//        matchService.getScores(userMatch, homeScores, awayScores);
//        matchService.FinishMatches(matches);
//        matchService.UserMatchEnd(userMatch, loggedUser());
//        mav.addObject("matches", matches);
//        mav.addObject("match", userMatch);
//        mav.addObject("stadium", loggedUser().getTeam().getStadium());
//        mav.addObject("homeScores", homeScores);
//        mav.addObject("awayScores", awayScores);

        return mav;
    }
}
