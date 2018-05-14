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

import java.util.List;

@org.springframework.stereotype.Controller
public class HomeController extends Controller{

    private Team team;
    private Player currentPlayer;
    private List<Player> players;


    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ContractService contractService;

    @RequestMapping("/home")
    public ModelAndView home(){
        team = loggedUser().getTeam();

//        if(team == null)
//            return new ModelAndView("redirect:chooseTeam");

//        players = team.getPlayers();
//        long playerId = players.get(0).getId();
        int playerId = 1;
        return home(playerId);
    }

    @RequestMapping("/home/{playerId}")
    public ModelAndView home(@PathVariable int playerId) {
        ModelAndView mav = new ModelAndView("home");
//        mav.addObject("team", team);
//        mav.addObject("players", players);
        mav.addObject("player", playerService.findById(playerId));
        return mav;
    }

    @RequestMapping("/retirePlayer")
    public ModelAndView retirePlayer() {
        playerService.retire(currentPlayer);
        return new ModelAndView("redirect:home");
    }

    @RequestMapping("/youthAcademy")
    public ModelAndView youthAcademy() {
        ModelAndView mav = new ModelAndView("youthAcademy");
//        mav.addObject("players", team.getYouthAcademy());
        return mav;
    }

    @RequestMapping("/finance")
    public ModelAndView finance() {
        ModelAndView mav = new ModelAndView("finance");
//        mav.addObject("money", team.getMoney());
        return mav;
    }


}
