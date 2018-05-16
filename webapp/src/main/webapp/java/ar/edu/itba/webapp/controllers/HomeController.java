package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.ContractService;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
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

    @RequestMapping("/home")
    public ModelAndView home(){
        Team team = teamService.findByUserId(loggedUser().getId());

        if(team == null)
            return new ModelAndView("redirect:chooseTeam");

        long playerId = team.getPlayers().get(0).getId();
        return home((int)playerId);
    }

    @RequestMapping("/home/{playerId}")
    public ModelAndView home(@PathVariable int playerId) {
        ModelAndView mav = new ModelAndView("home");

        Team team = teamService.findByUserId(loggedUser().getId());

        Player currentPlayer = playerService.findById(playerId);

        mav.addObject("team", team);
        mav.addObject("players", team.getPlayers());
        mav.addObject("player", playerService.findById(playerId));

        return mav;
    }

    @RequestMapping("/retirePlayer")
    public ModelAndView retirePlayer() {
//        Player currentPlayer = playerService.findById(playerId);
//
//        playerService.retire(currentPlayer);
        return new ModelAndView("redirect:home");
    }

//    @RequestMapping("/youthAcademy")
//    public ModelAndView youthAcademy() {
//        ModelAndView mav = new ModelAndView("youthAcademy");
////        mav.addObject("players", team.getYouthAcademy());
//        return mav;
//    }
}
