package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.TransferService;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.PlayerDTO;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Path("players")
@Component
public class PlayerController extends Controller {

    @Autowired
    private TransferService transferService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getPlayers() {
        Team team = teamService.findByUserIdAndFetchPlayers(loggedUser().getId());
        if (team != null) {
            List<Player> players = playerService.getPlayers(team);
            List<PlayerDTO> playerDTOS = new LinkedList<>();

            for (Player player : players) {
                playerDTOS.add(new PlayerDTO(player));
            }

            return Response.ok(playerDTOS).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @POST
    @Path("/buy")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response buyPlayer(final long playerId) {
        boolean success = transferService.performTransfer(loggedUser(), playerId);
        if(success)
            return Response.ok().build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
