package ar.edu.itba.webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class FormationController extends Controller{

    @RequestMapping("/formation")
    public ModelAndView formation() {
        ModelAndView mav = new ModelAndView("formation");
        return mav;
    }
}
