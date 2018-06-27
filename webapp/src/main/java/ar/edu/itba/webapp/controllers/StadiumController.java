package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.EconomyService;
import ar.edu.itba.interfaces.service.StadiumService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.webapp.form.StadiumForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class StadiumController extends Controller{

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private EconomyService economyService;

    @RequestMapping("/stadium")
    public ModelAndView stadium() {
        ModelAndView mav = new ModelAndView("stadium");
        mav.addObject("stadium", stadiumService.findByTeam(teamService.findByUserId(loggedUser().getId())));
        return mav;
    }

    @RequestMapping(value = "/upgradingStadium", method = RequestMethod.POST)
    @ResponseBody
    public Object processSurvey(@ModelAttribute("StadiumForm") StadiumForm form){
        User user = loggedUser();
        Team team = teamService.findByUserIdAndFetchPlayersAndFormation(user.getId());
        return stadiumService.upgradeStadium(team, form.getLowClass(), form.getMediumClass(), form.getHighClass());
    }
}