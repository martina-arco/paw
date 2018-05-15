package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class MatchController extends Controller{

//    @Autowired
//    private MatchService matchService;

//    @Autowired
//    private StadiumService stadiumService;

    @Autowired
    private SimulationService simulationService;

    @RequestMapping("/match")
    public ModelAndView match() {
        ModelAndView mav = new ModelAndView("match");
//        mav.addObject("matches", );
//        simulationService.start();
        return mav;
    }

    @RequestMapping(value = "/data", produces = "application/json")
    @ResponseBody
    public Object json() {
        Map<Integer, List<Event>> map = new HashMap<>();
//        map = simulationService.getEvents();

        List<Event> l1 = new ArrayList<>();
        List<Event> l2 = new ArrayList<>();
        l1.add(new Event(1,null, Event.Type.YELLOW_CARD, 1));
        l1.add(new Event(2,null, Event.Type.RED_CARD, 1));
        l1.add(new Event(2,null, Event.Type.SCORE, 1));
        l2.add(new Event(3,null, Event.Type.RED_CARD, 1));
        l2.add(new Event(4,null, Event.Type.ASSIST, 1));
        map.put(1,l1);
        map.put(2,l2);

        return map;
    }

    private ModelAndView matchEnds() {
        return new ModelAndView("redirect:matchEnd");
    }

    @RequestMapping("/matchEnd")
    public ModelAndView matchEnd() {
        ModelAndView mav = new ModelAndView("matchEnd");
//        int matchId = matchService.findByTeamId(getTeamId());
//        mav.addObject("match", matchService.findById(matchId));
//        mav.addObject("stadium", matchService.getStadiumById(matchId));
//        mav.addObject("homeSaves", matchService.getHomeSaves(matchId));
//        mav.addObject("awaySaves", matchService.getAwaySaves(matchId));
//        mav.addObject("homeTackles", matchService.getHomeTackles(matchId));
//        mav.addObject("awayTackles", matchService.getAwayTackles(matchId));
//        mav.addObject("homeAssists", matchService.getHomeAssists(matchId));
//        mav.addObject("awayAssists", matchService.getAwayAssists(matchId));
//        mav.addObject("homeFouls", matchService.getHomeFouls(matchId));
//        mav.addObject("awayFouls", matchService.getAwayFouls(matchId));
        return mav;
    }
}
