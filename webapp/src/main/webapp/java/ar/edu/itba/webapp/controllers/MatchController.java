package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class MatchController extends Controller{

    @Autowired
    private MatchService matchService;

    @Autowired
    private LeagueService leagueService;

//    @Autowired
//    private StadiumService stadiumService;

    @RequestMapping("/match")
    public ModelAndView match() {
        ModelAndView mav = new ModelAndView("match");
//        mav.addObject("matches", );
        return mav;
    }

    @RequestMapping("/matchEnd")
    public ModelAndView matchEnd() {
        ModelAndView mav = new ModelAndView("matchEnd");
        List<Match> matches = leagueService.findByUser(loggedUser()).get(0).getFixture().get(loggedUser().getCurrentDay());
        Match userMatch = matchService.getUserMatch(matches, loggedUser().getTeam());
        matchService.FinishMatches(matches);
        matchService.UserMatchEnd(userMatch, loggedUser());
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
