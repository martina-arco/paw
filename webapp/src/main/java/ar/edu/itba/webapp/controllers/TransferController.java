package ar.edu.itba.webapp.controllers;


import ar.edu.itba.interfaces.service.TransferService;
import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.PlayerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class TransferController extends Controller {

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserService userService;

    @RequestMapping("/transfers")
    public ModelAndView transfer(){
        ModelAndView mav = new ModelAndView("transfer");
        List<String> inputs = PlayerFilter.values();

        mav.addObject("criterias", inputs);
        return mav;
    }

    @RequestMapping(value = "/transferFilter", produces = "application/json")
    @ResponseBody
    public Object json(@RequestBody String filters) {
        return transferService.playersByCriteria(loggedUser(), transferService.criteria(filters));
    }

    @RequestMapping(value = "/transferPlayer")
    @ResponseBody
    public Object transferredPlayer(@RequestBody String transfer){
//        return transferService.performTransfer(loggedUser(), transfer);
        return null;
    }

}
