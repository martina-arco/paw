package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("/home")
public class HomeController {

//    @Autowired
//    private PlayerService player;
//    private TeamService player;

    @RequestMapping("/home")
    public ModelAndView home(/*@RequestParam(value = "id", required = true) final int id*/) {
        ModelAndView mav = new ModelAndView("home");
//        mav.addObject("player", player.findById(id));
//        mav.addObject("team", team.findById(id));
        return mav;
    }
}
