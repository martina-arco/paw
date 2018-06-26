package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.*;
import ar.edu.itba.webapp.form.ChooseTeamForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/chooseTeam")
    public ModelAndView chooseTeam(@ModelAttribute("chooseTeamForm") final ChooseTeamForm form) {
        return new ModelAndView("chooseTeam");
    }

    @RequestMapping(value = "/processForm", method = RequestMethod.POST)
    public ModelAndView processSurvey(@Valid @ModelAttribute("chooseTeamForm") ChooseTeamForm form, BindingResult result) {

        if (result.hasErrors()) {
            return chooseTeam(form);
        }

        Team t = teamService.findById(form.getTeamChosen());

        userService.setTeam(loggedUser(), t);

        return new ModelAndView("redirect:/");
    }

    @ModelAttribute("teamList")
    public List<Team> getTeamList(){

        League league = leagueService.findByUser(loggedUser()).get(0);

        List<Team> teams = teamService.findByLeague(league);

        return teams;
    }
}
