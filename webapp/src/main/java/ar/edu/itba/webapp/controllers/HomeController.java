package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class HomeController extends Controller {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView home(){
        Team team = teamService.findByUserIdAndFetchPlayers(loggedUser().getId());

        if(team == null)
            return new ModelAndView("redirect:chooseTeam");

        long playerId;

        if(team.getPlayers().size() > 0)
            playerId = team.getPlayers().get(0).getId();
        else
            playerId = 0;

        return home((int)playerId);
    }

    @RequestMapping("/{playerId}")
    public ModelAndView home(@PathVariable int playerId) {
        ModelAndView mav = new ModelAndView("home");

        User user = loggedUser();
        Team team = teamService.findByUserIdAndFetchPlayers(user.getId());
        Player toDisplay = playerService.findById(playerService.getPlayers(team), playerId);

        if(toDisplay == null){
            return new ModelAndView("redirect:/");
        }

        List<Match> matches = matchService.getUpcomingMatches(team, user.getCurrentDay());

        if(matches.size() > 0) {
            Match nextMatch = matches.get(0);
            mav.addObject("team1", nextMatch.getHome().getName());
            mav.addObject("team2", nextMatch.getAway().getName());
            mav.addObject("stadium", nextMatch.getHome().getStadium().getName());
        } else {
            mav.addObject("team1", "No team");
            mav.addObject("team2", "no team");
            mav.addObject("stadium", "no stadium");
        }



        mav.addObject("team", team);
        mav.addObject("date", userService.getCurrentDay(user));
        mav.addObject("players", playerService.getPlayers(team));
        mav.addObject("player", toDisplay);

        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        return new ModelAndView("404");
    }

//    @RequestMapping("/retirePlayer")
//    public ModelAndView retirePlayer() {
////        Player currentPlayer = playerService.findById(playerId);
////
////        playerService.retire(currentPlayer);
//        return new ModelAndView("redirect:home");
//    }

//    @RequestMapping("/youthAcademy")
//    public ModelAndView youthAcademy() {
//        ModelAndView mav = new ModelAndView("youthAcademy");
////        mav.addObject("players", team.getYouthAcademy());
//        return mav;
//    }
}
