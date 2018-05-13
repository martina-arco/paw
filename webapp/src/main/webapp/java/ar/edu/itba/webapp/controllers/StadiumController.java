package ar.edu.itba.webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class StadiumController extends Controller{

//    @Autowired
//    private StadiumService stadiumService;

    @RequestMapping("/stadium")
    public ModelAndView stadium() {
        ModelAndView mav = new ModelAndView("stadium");
//        mav.addObject("stadium", stadiumService.findByTeamId(getTeamId());
        return mav;
    }
}