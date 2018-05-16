package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.*;
import ar.edu.itba.webapp.form.ChooseTeamForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class ChooseTeamController extends Controller{

    private League league;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/chooseTeam")
    public ModelAndView chooseTeam(@ModelAttribute("chooseTeamForm") final ChooseTeamForm form) {
//        league = leagueService.findByUser(loggedUser());
        return new ModelAndView("chooseTeam");
    }

    @RequestMapping(value = "/processForm", method = RequestMethod.POST)
    public ModelAndView processSurvey(@Valid @ModelAttribute("chooseTeamForm") ChooseTeamForm form, BindingResult result) {

        if (result.hasErrors()) {
            return chooseTeam(form);
        }

//        userService.setTeam(loggedUser(), teamService.findById(form.getTeamChosen()));

        return new ModelAndView("redirect:home");
    }

    @ModelAttribute("teamList")
    public List<Team> getTeamList(){

//        List<Team> teams = teamService.findByLeague(league);


        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1,"River",null,null,null,new ArrayList<Player>(), new ArrayList<Player>(), 0, 0,0,new ArrayList<Receipt>(), new ArrayList<BankLoan>(),0));
        teams.add(new Team(2,"Boca",null,null,null,new ArrayList<Player>(), new ArrayList<Player>(), 0, 0,0,new ArrayList<Receipt>(), new ArrayList<BankLoan>(),0));
        teams.add(new Team(3,"Racing",null,null,null,new ArrayList<Player>(), new ArrayList<Player>(), 0, 0,0,new ArrayList<Receipt>(), new ArrayList<BankLoan>(),0));


        return teams;
    }
}
