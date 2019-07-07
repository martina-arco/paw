package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.PlayerDTO;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
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
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getPlayers() {
        Team team = teamService.findByUserIdAndFetchPlayers(loggedUser().getId());
        List<Player> players = playerService.getPlayers(team);
        List<PlayerDTO> playerDTOS = new LinkedList<>();

        for (Player player : players) {
            playerDTOS.add(new PlayerDTO(player));
        }

        return Response.ok(playerDTOS).build();
    }


//    @POST
//    @Path("player/buy")
//    @Produces(value = { MediaType.APPLICATION_JSON, })
//    public Response buyPlayer(final PlayerDTO player) {
//        return Response.created(uri).build();
//    }
//
//    @POST
//    @Path("player/sell")
//    @Produces(value = { MediaType.APPLICATION_JSON, })
//    public Response sellPlayer(final PlayerDTO player) {
//        return Response.created(uri).build();
//    }
}
