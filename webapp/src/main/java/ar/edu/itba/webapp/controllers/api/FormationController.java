package ar.edu.itba.webapp.controllers.api;

import ar.edu.itba.interfaces.service.FormationService;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.User;
import ar.edu.itba.model.utils.Point;
import ar.edu.itba.webapp.controllers.Controller;
import ar.edu.itba.webapp.model.DTOs.FormationDTO;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Team;
import ar.edu.itba.webapp.model.DTOs.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("formation")
@Component
public class FormationController extends Controller {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private FormationService formationService;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getFormation() {
        Team team = teamService.findByUserIdAndFetchPlayersAndFormation(loggedUser().getId());
        Formation formation = team.getFormation();
        return Response.ok(new FormationDTO(formation)).build();
    }

    @POST
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response saveFormation(final FormationDTO formation) {
        User user = loggedUser();
        Team team = teamService.findByUserIdAndFetchPlayersAndFormation(user.getId());

        Player gk = formation.getGk() != null ? playerService.findById(formation.getGk().getId()) : null;
        Player lb = formation.getLb() != null ? playerService.findById(formation.getLb().getId()) : null;
        Player lcb = formation.getLcb() != null ? playerService.findById(formation.getLcb().getId()) : null;
        Player cb = formation.getCb() != null ? playerService.findById(formation.getCb().getId()) : null;
        Player rcb= formation.getRcb() != null ? playerService.findById(formation.getRcb().getId()) : null;
        Player rb = formation.getRb() != null ? playerService.findById(formation.getRb().getId()) : null;
        Player lm = formation.getLm() != null ? playerService.findById(formation.getLm().getId()) : null;
        Player lcm = formation.getLcm() != null ? playerService.findById(formation.getLcm().getId()) : null;
        Player cdm = formation.getCdm() != null ? playerService.findById(formation.getCdm().getId()) : null;
        Player cam = formation.getCam() != null ? playerService.findById(formation.getCam().getId()) : null;
        Player rcm = formation.getRcm() != null ? playerService.findById(formation.getRcm().getId()) : null;
        Player rm = formation.getRm() != null ? playerService.findById(formation.getRm().getId()) : null;
        Player lw = formation.getLw() != null ? playerService.findById(formation.getLw().getId()) : null;
        Player lf = formation.getLf() != null ? playerService.findById(formation.getLf().getId()) : null;
        Player st = formation.getSt() != null ? playerService.findById(formation.getSt().getId()) : null;
        Player rf = formation.getRf() != null ? playerService.findById(formation.getRf().getId()) : null;
        Player rw = formation.getRw() != null ? playerService.findById(formation.getRw().getId()) : null;

        Map<Player, Point> starters = formationService.createStarters(gk,lb,lcb,cb,rcb,rb,cdm,lm,lcm,rcm,rm,cam,lw,lf,st,rf,rw);
        List<Player> substitutes = new ArrayList<>();

        for (PlayerDTO playerDTO: formation.getSubstitutes()) {
            substitutes.add(playerService.findById(playerDTO.getId()));
        }

        Formation formationToSave = team.getFormation();
        formationToSave.setStarters(starters);
        formationToSave.setSubstitutes(substitutes);
        formationToSave.setCaptain(playerService.findById(formation.getCaptain().getId()));
        formationToSave.setFreeKickTaker(playerService.findById(formation.getFreeKickTaker().getId()));
        formationToSave.setPenaltyTaker(playerService.findById(formation.getPenaltyTaker().getId()));
        formationToSave.setPressure(Formation.Pressures.valueOf(formation.getPressure()).ordinal());
        formationToSave.setAttitude(Formation.Attitudes.valueOf(formation.getAttitude()).ordinal());

        if(formationService.isValid(formationToSave)) {
            team.setFormation(formationToSave);
            formationService.save(formationToSave);
            return Response.ok().build();
        }

        return Response.status(400).build();
    }

}
