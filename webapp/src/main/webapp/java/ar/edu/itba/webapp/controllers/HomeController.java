package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/home")
    public ModelAndView home(){
        Team team = teamService.findByUserId(loggedUser().getId());

        if(team == null)
            return new ModelAndView("redirect:chooseTeam");

        long playerId;
        teamService.setPlayers(team);

        if(team.getPlayers().size() > 0)
            playerId = team.getPlayers().get(0).getId();
        else
            playerId = 0;

        return home((int)playerId);
    }

    @RequestMapping("/home/{playerId}")
    public ModelAndView home(@PathVariable int playerId) {
        ModelAndView mav = new ModelAndView("home");

        User user = loggedUser();
        Team team = teamService.findByUserId(user.getId());
        teamService.setPlayers(team);
        Match nextMatch = matchService.getUpcomingMatches(team, user.getCurrentDay()).get(0);


        mav.addObject("team", team);
        mav.addObject("team1",nextMatch.getHome());
        mav.addObject("team2", nextMatch.getAway());
        mav.addObject("stadium", nextMatch.getHome().getStadium());
        mav.addObject("date", userService.getCurrentDay(user));
        mav.addObject("players", playerService.getPlayers(team));
        mav.addObject("player", playerService.findById(playerId));

        return mav;
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
