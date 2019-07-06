package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.TransferService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.PlayerDTO;
import ar.edu.itba.webapp.model.DTOs.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Path("transfers")
@Component
public class TransferController extends Controller {

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransferService transferService;

    @PUT
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getPlayers(final String filters) {
        List<Player> players = transferService.playersByCriteria(loggedUser(), transferService.criteria(filters));
        return Response.ok(players.parallelStream().map(PlayerDTO::new).collect(Collectors.toList())).build();
    }

    @POST
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response transferPlayer(final long playerId) {
        User user = loggedUser();
        Player player = playerService.findById(playerId);

        if (player != null && player.getTeam().getLeague().getUserId() == user.getId()) {
            if (transferService.transferPlayer(player.getTeam(), user.getTeam(), player)) {
                Team userTeam = teamService.findByUserIdAndFetchPlayers(user.getId());
                return Response.ok(new TeamDTO(userTeam)).build();
            } else {
                return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}