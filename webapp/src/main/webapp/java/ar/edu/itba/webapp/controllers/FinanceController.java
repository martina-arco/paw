package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class FinanceController extends Controller {

    @Autowired
    TeamService teamService;

    @RequestMapping("/finance")
    public ModelAndView finance() {
        ModelAndView mav = new ModelAndView("finance");

        Team team = teamService.findByUserId(loggedUser().getId());
        teamService.setPlayers(team);
        teamService.setFinance(team);

        int salaries = teamService.getSalaries(team);
        int ticketsSold = teamService.getTicketsSold(team);

        mav.addObject("money", team.getMoney());
        mav.addObject("salaries", salaries);
        mav.addObject("ticketsSold", ticketsSold);

        return mav;
    }

}
