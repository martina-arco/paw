package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.User;
import ar.edu.itba.webapp.form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private UserService us;

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("registerForm") final UserForm form) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final UserForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return index(form);
        }
        final User u = us.create(form.getUsername(), form.getPassword());
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/user")
    public ModelAndView index(@RequestParam(value = "userId", required = true) final int id) {
        final ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", us.findById(id));
        return mav;
    }

    @RequestMapping(value="/hello")
    public ModelAndView printUser() {
        final ModelAndView mav = new ModelAndView("hello");
        return mav;
    }


    @ModelAttribute
    public User loggedUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final User user = us.findByUsername(auth.getName());
        LOGGER.debug("Logged user is {}", user);
        return user;
    }

}