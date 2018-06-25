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
        List<Player> players = team.getPlayers();
        Formation formation = team.getFormation();
        List<Player> substitutes = formation.getSubstitutes();
        mav.addObject("players", players);
        mav.addObject("substitutes", substitutes);
        mav.addObject("gk", formation.getGk());
        mav.addObject("lb", formation.getLb());
        mav.addObject("lcb", formation.getLcb());
        mav.addObject("rcb", formation.getRcb());
        mav.addObject("rb", formation.getRb());
        mav.addObject("lm", formation.getLm());
        mav.addObject("lcm", formation.getLcm());
        mav.addObject("rcm", formation.getRcm());
        mav.addObject("rm", formation.getRm());
        mav.addObject("lf", formation.getLf());
        mav.addObject("rf", formation.getRf());
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
        Player rcb= playerService.findById(form.getRightCenterBack());
        Player rb = playerService.findById(form.getRightBack());
        Player lm = playerService.findById(form.getLeftMid());
        Player lcm = playerService.findById(form.getLeftCenterMid());
        Player rcm = playerService.findById(form.getRightCenterMid());
        Player rm = playerService.findById(form.getRightMid());
        Player lf = playerService.findById(form.getLeftForward());
        Player rf = playerService.findById(form.getRightForward());

        Map<Player, Point> positions = new HashMap<>();
        positions.put(gk, new Point(0,4));
        positions.put(lb, new Point(1, 1));
        positions.put(lcb, new Point(1, 3));
        positions.put(rb, new Point(1, 7));
        positions.put(rcb, new Point(1, 5));
        positions.put(lm, new Point(5, 4));
        positions.put(lcm, new Point(4, 1));
        positions.put(rcm, new Point(4, 7));
        positions.put(rm, new Point(3, 4));
        positions.put(lf, new Point(7, 3));
        positions.put(rf, new Point(7, 5));

        List<Player> substitutes = new ArrayList<>();
        substitutes.add(playerService.findById(form.getSubstitute1()));
        substitutes.add(playerService.findById(form.getSubstitute2()));
        substitutes.add(playerService.findById(form.getSubstitute3()));
        substitutes.add(playerService.findById(form.getSubstitute4()));
        substitutes.add(playerService.findById(form.getSubstitute5()));
        substitutes.add(playerService.findById(form.getSubstitute6()));
        substitutes.add(playerService.findById(form.getSubstitute7()));

        Formation formation = team.getFormation();
        formation.changeFormation(form.getFormation(), positions);
        formation.setSubstitutes(substitutes);
        formation.setCaptain(playerService.findById(form.getCaptain()));
        formation.setFreeKickTaker(playerService.findById(form.getFreeKickTaker()));
        formation.setPenaltyTaker(playerService.findById(form.getPenaltyTaker()));
        formation.setPressure(form.getPressure());
        formation.setAttitude(form.getAttitude());
        team.setFormation(formation);
        formationService.save(formation);

        return new ModelAndView("redirect:home");
    }

}
