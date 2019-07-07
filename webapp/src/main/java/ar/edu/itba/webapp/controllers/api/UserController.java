package ar.edu.itba.webapp.controllers.api;


import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.MatchDTO;
import ar.edu.itba.webapp.model.DTOs.UserDTO;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response.*;

@Path("/")
@Component
public class UserController extends Controller {
    @Autowired
    private UserService userService;
    @Context
    private UriInfo uriInfo;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private SimulationService simulationService;


    @GET
    @Path("user")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getCurrent() {
        final User user = loggedUser();
        if (user != null) {
            return Response.ok(new UserDTO(user)).build();
        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("register")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response createUser(final UserDTO userDto) {
        if (userDto != null) {
            final User user = userService.create(userDto.getUsername(), userDto.getPassword(), userDto.getMail(), new Date());
            if (user != null) {
                final URI uri = uriInfo.getAbsolutePathBuilder().path("user").build();
                return Response.created(uri).build();
            }
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @POST
    @Path("user/advanceDate")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response advanceDate() {
        User user = loggedUser();
        if (user == null)
            return Response.status(Status.UNAUTHORIZED).build();

        League league = leagueService.findByUser(user).get(0);
        List<Match> matches = leagueService.findMatchesForDate(league, user.getCurrentDay());
        Match userMatch = matchService.getUserMatch(matches, user);

        if (userMatch != null && simulationService.started(userMatch)) {

            for (Match match : matches) {
                matchService.payTickets(match);
            }

            userService.advanceDate(user);

            return Response.ok(new MatchDTO(userMatch)).build();
        }

        return Response.status(400).build();
    }
}