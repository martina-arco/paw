package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.StadiumService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.EconomyDTO;
import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;
import ar.edu.itba.webapp.model.DTOs.StadiumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Path("finance")
@Component
public class FinanceController extends Controller {

    @Autowired
    private TeamService teamService;

    @Autowired
    private StadiumService stadiumService;

    @Context
    private UriInfo uriInfo;


    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getEconomy() {
        Team team = teamService.findByUserIdAndFetchPlayersAndFinance(loggedUser().getId()); //id del user
        Map<Receipt.Type, Integer> summary = teamService.getFinanceSummary(team);

        int income, playersSold, ticketsSold, expenses, playersBought, salaries, stadiumExpansion;
        playersSold = summary.get(Receipt.Type.SOLDPLAYER);
        ticketsSold = summary.get(Receipt.Type.MATCHINCOME);
        playersBought = summary.get(Receipt.Type.BOUGHTPLAYER);
        salaries = summary.get(Receipt.Type.PLAYERSSALARIES);
        stadiumExpansion = summary.get(Receipt.Type.EXPANDEDSTADIUM);
        income = playersSold + ticketsSold;
        expenses = playersBought + salaries + stadiumExpansion;

        return Response.ok(new EconomyDTO(income, expenses, team.getMoney(), summary)).build();
    }

    @GET
    @Path("/receipts")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getReceipts() {
        Team team = teamService.findByUserIdAndFetchPlayersAndFinance(loggedUser().getId());
        List<Receipt> receipts = team.getFinance();

        List<Receipt> sortedReceipts = new LinkedList<>(receipts);
        sortedReceipts.sort((Receipt r1, Receipt r2)-> (int)(r2.getId() - r1.getId()));

        return Response.ok(sortedReceipts.subList(0, Math.min(9, receipts.size()))).build();
    }

    @GET
    @Path("/stadium")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getStadiumFinance() {
        Team team = teamService.findByUserIdAndFetchPlayersAndFinance(loggedUser().getId());
        Stadium stadium = stadiumService.findByTeam(team);

        return Response.ok(new StadiumDTO(stadium)).build();
    }

//  TODO

//    @POST
//    @Path("/stadium")
//    @Produces(value = { MediaType.APPLICATION_JSON, })
//    public Response saveStadiumFinance(final StadiumDTO userDto) {
//        return Response.created(null).build();
//    }


}