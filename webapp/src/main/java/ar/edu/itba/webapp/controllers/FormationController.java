package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.FormationService;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.model.utils.Point;
import ar.edu.itba.webapp.form.ChooseTeamForm;
import ar.edu.itba.webapp.form.FormationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class FormationController extends Controller{

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private FormationService formationService;

    @RequestMapping(value = "/formation")
    public ModelAndView formation(@ModelAttribute("formationForm") final FormationForm form) {
        ModelAndView mav = new ModelAndView("formation");
        User user = loggedUser();
        Team team = teamService.findByUserIdAndFetchPlayersAndFormation(user.getId());
        Formation formation = team.getFormation();
        List<Player> substitutes = formation.getSubstitutes();
        mav.addObject("players", team.getPlayers());
        mav.addObject("starters", formation.getStarters().keySet());
        mav.addObject("gk", formation.getGk());
        mav.addObject("lb", formation.getLb());
        mav.addObject("lcb", formation.getLcb());
        mav.addObject("cb", formation.getCb());
        mav.addObject("rcb", formation.getRcb());
        mav.addObject("rb", formation.getRb());
        mav.addObject("lm", formation.getLm());
        mav.addObject("lcm", formation.getLcm());
        mav.addObject("cdm", formation.getCdm());
        mav.addObject("cam", formation.getCam());
        mav.addObject("rcm", formation.getRcm());
        mav.addObject("rm", formation.getRm());
        mav.addObject("lw", formation.getLw());
        mav.addObject("lf", formation.getLf());
        mav.addObject("st", formation.getSt());
        mav.addObject("rf", formation.getRf());
        mav.addObject("rw", formation.getRw());
        mav.addObject("substitutes", substitutes);
        if(substitutes.size() == 7) {
            mav.addObject("sub1", substitutes.get(0));
            mav.addObject("sub2", substitutes.get(1));
            mav.addObject("sub3", substitutes.get(2));
            mav.addObject("sub4", substitutes.get(3));
            mav.addObject("sub5", substitutes.get(4));
            mav.addObject("sub6", substitutes.get(5));
            mav.addObject("sub7", substitutes.get(6));
        }
        mav.addObject("captain", formation.getCaptain());
        mav.addObject("fk", formation.getFreeKickTaker());
        mav.addObject("pen", formation.getPenaltyTaker());
        mav.addObject("pressures", formation.getPossiblePressures());
        mav.addObject("pressure", formation.getPressure());
        mav.addObject("attitudes", formation.getPossibleAttitudes());
        mav.addObject("attitude", formation.getAttitude());
        mav.addObject("formations", formation.getPossibleFormations());
        mav.addObject("formation", formation.getFormationPositions());

        return mav;
    }

    @RequestMapping(value = "/processFormationForm", method = RequestMethod.POST)
    public ModelAndView processSurvey(@Valid @ModelAttribute("formationForm") FormationForm form, BindingResult result) {

        if (result.hasErrors()) {
            return formation(form);
        }

        User user = loggedUser();
        Team team = teamService.findByUserIdAndFetchPlayersAndFormation(user.getId());

        Player gk = playerService.findById(form.getGoalkeeper());
        Player lb = playerService.findById(form.getLeftBack());
        Player lcb = playerService.findById(form.getLeftCenterBack());
        Player cb = playerService.findById(form.getCenterBack());
        Player rcb= playerService.findById(form.getRightCenterBack());
        Player rb = playerService.findById(form.getRightBack());
        Player lm = playerService.findById(form.getLeftMid());
        Player lcm = playerService.findById(form.getLeftCenterMid());
        Player cdm = playerService.findById(form.getCenterDefensiveMid());
        Player cam = playerService.findById(form.getCenterAttackingMid());
        Player rcm = playerService.findById(form.getRightCenterMid());
        Player rm = playerService.findById(form.getRightMid());
        Player lw = playerService.findById(form.getLeftWing());
        Player lf = playerService.findById(form.getLeftForward());
        Player st = playerService.findById(form.getStriker());
        Player rf = playerService.findById(form.getRightForward());
        Player rw = playerService.findById(form.getRightWing());

        Map<Player, Point> positions = new HashMap<>();
        if(gk != null)
            positions.put(gk, new Point(0,4));
        if(lb != null)
            positions.put(lb, new Point(1, 7));
        if(lcb != null)
            positions.put(lcb, new Point(1, 5));
        if(cb != null)
            positions.put(cb, new Point(1, 4));
        if(rcb != null)
            positions.put(rcb, new Point(1, 3));
        if(rb != null)
            positions.put(rb, new Point(1, 1));
        if(cdm != null)
            positions.put(cdm, new Point(3, 4));
        if(lm != null)
            positions.put(lm, new Point(4, 7));
        if(lcm != null)
            positions.put(lcm, new Point(4, 5));
        if(rcm != null)
            positions.put(rcm, new Point(4, 3));
        if(rm != null)
            positions.put(rm, new Point(4, 1));
        if(cam != null)
            positions.put(cam, new Point(5, 4));
        if(lw != null)
            positions.put(lw, new Point(7, 7));
        if(lf != null)
            positions.put(lf, new Point(7, 5));
        if(st != null)
            positions.put(st, new Point(7, 4));
        if(rf != null)
            positions.put(rf, new Point(7, 3));
        if(rw != null)
            positions.put(rw, new Point(7, 1));

        List<Player> substitutes = new ArrayList<>();
        substitutes.add(playerService.findById(form.getSubstitute1()));
        substitutes.add(playerService.findById(form.getSubstitute2()));
        substitutes.add(playerService.findById(form.getSubstitute3()));
        substitutes.add(playerService.findById(form.getSubstitute4()));
        substitutes.add(playerService.findById(form.getSubstitute5()));
        substitutes.add(playerService.findById(form.getSubstitute6()));
        substitutes.add(playerService.findById(form.getSubstitute7()));

        Formation formation = team.getFormation();
        formation.setStarters(positions);
        formation.setSubstitutes(substitutes);
        formation.setCaptain(playerService.findById(form.getCaptain()));
        formation.setFreeKickTaker(playerService.findById(form.getFreeKickTaker()));
        formation.setPenaltyTaker(playerService.findById(form.getPenaltyTaker()));
        formation.setPressure(form.getPressure());
        formation.setAttitude(form.getAttitude());
        team.setFormation(formation);
        formationService.save(formation);

        return new ModelAndView("redirect:/");
    }

}
