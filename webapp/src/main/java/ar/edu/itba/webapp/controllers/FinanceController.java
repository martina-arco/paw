package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class FinanceController extends Controller {

    @Autowired
    TeamService teamService;

    @RequestMapping("/finance")
    public ModelAndView finance() {
        ModelAndView mav = new ModelAndView("finance");

        Team team = teamService.findByUserIdAndFetchPlayersAndFinance(loggedUser().getId());

        int salaries = teamService.getSalaries(team);
        int ticketsSold = teamService.getTicketsSold(team);
        List<Receipt> receipts = team.getFinance();

        mav.addObject("money", team.getMoney());
        mav.addObject("salaries", salaries);
        mav.addObject("ticketsSold", ticketsSold);
        mav.addObject("lastReceipts", receipts.subList(0, Math.min(10, receipts.size())));

        return mav;
    }

}
