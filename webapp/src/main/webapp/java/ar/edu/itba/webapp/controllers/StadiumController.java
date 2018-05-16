package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.StadiumService;
import ar.edu.itba.interfaces.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class StadiumController extends Controller{

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private TeamService teamService;

    @RequestMapping("/stadium")
    public ModelAndView stadium() {
        ModelAndView mav = new ModelAndView("stadium");
        mav.addObject("stadium", stadiumService.findByTeam(teamService.findByUserId(loggedUser().getId())));
        return mav;
    }
}