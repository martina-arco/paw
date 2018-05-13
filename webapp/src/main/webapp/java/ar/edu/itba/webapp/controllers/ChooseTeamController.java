package ar.edu.itba.webapp.controllers;

import ar.edu.itba.webapp.form.ChooseTeamForm;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class ChooseTeamController extends Controller{

    @RequestMapping(value = "/chooseTeam")
    public ModelAndView chooseTeam(@ModelAttribute("chooseTeamForm") final ChooseTeamForm form) {
        return new ModelAndView("chooseTeam");
    }

    @RequestMapping(value = "/processForm", method = RequestMethod.POST)
    public ModelAndView processSurvey(@Valid @ModelAttribute("chooseTeamForm") ChooseTeamForm form, BindingResult result) {

        if (result.hasErrors()) {
            return chooseTeam(form);
        }

//        loggedUser().setTeam(form.getTeamChosen());

        return new ModelAndView("redirect:home/1");
    }

    @ModelAttribute("teamList")
    public List<String> getTeamList(){

        List<String> teamList = new ArrayList<>();
        teamList.add("River");
        teamList.add("Boca");
        teamList.add("San lorenzo");
        //TO DO agarrar todos los nombres de equipos

        return teamList;
    }
}
