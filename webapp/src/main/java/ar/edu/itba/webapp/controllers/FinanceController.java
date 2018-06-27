package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.StadiumService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;
import ar.edu.itba.webapp.form.StadiumForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class FinanceController extends Controller {

    @Autowired
    private TeamService teamService;

    @Autowired
    private StadiumService stadiumService;

    @RequestMapping("/finance")
    public ModelAndView finance(@ModelAttribute("stadiumForm") final StadiumForm form) {
        ModelAndView mav = new ModelAndView("finance");

        Team team = teamService.findByUserIdAndFetchPlayersAndFinance(loggedUser().getId());
        Stadium stadium = stadiumService.findByTeam(team);

        int salaries = teamService.getSalaries(team);
        int ticketsSold = teamService.getTicketsSold(team);
        List<Receipt> receipts = team.getFinance();

        mav.addObject("lowCost", Stadium.getLowCost());
        mav.addObject("mediumCost", Stadium.getMediumCost());
        mav.addObject("highCost", Stadium.getHighCost());
        mav.addObject("stadium", stadium);
        mav.addObject("money", team.getMoney());
        mav.addObject("salaries", salaries);
        mav.addObject("ticketsSold", ticketsSold);
        mav.addObject("lastReceipts", receipts.subList(0, Math.min(9, receipts.size())));

        return mav;
    }

}
