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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        Map<Receipt.Type, Integer> summary = teamService.getFinanceSummary(team);
        List<Receipt> receipts = team.getFinance();

        int income, playersSold, ticketsSold, expenses, playersBought, salaries, stadiumExpansion;
        playersSold = summary.get(Receipt.Type.SOLDPLAYER);
        ticketsSold = summary.get(Receipt.Type.MATCHINCOME);
        playersBought = summary.get(Receipt.Type.BOUGHTPLAYER);
        salaries = summary.get(Receipt.Type.PLAYERSSALARIES);
        stadiumExpansion = summary.get(Receipt.Type.EXPANDEDSTADIUM);
        income = playersSold + ticketsSold;
        expenses = playersBought + salaries + stadiumExpansion;
        List<Receipt> sortedReceipts = new LinkedList<>(receipts);
        sortedReceipts.sort((Receipt r1, Receipt r2)-> (int)(r2.getId() - r1.getId()));

        mav.addObject("lowCost", Stadium.getLowCost());
        mav.addObject("mediumCost", Stadium.getMediumCost());
        mav.addObject("highCost", Stadium.getHighCost());
        mav.addObject("stadium", stadium);
        mav.addObject("money", team.getMoney());
        mav.addObject("income", income);
        mav.addObject("playersSold", playersSold);
        mav.addObject("ticketsSold", ticketsSold);
        mav.addObject("expenses", expenses);
        mav.addObject("playersBought", playersBought);
        mav.addObject("salaries", salaries);
        mav.addObject("stadiumExpansion", stadiumExpansion);

        mav.addObject("lastReceipts", sortedReceipts.subList(0, Math.min(9, receipts.size())));

        return mav;
    }

}
