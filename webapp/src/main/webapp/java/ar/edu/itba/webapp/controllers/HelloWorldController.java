package ar.edu.itba.webapp.controllers;

import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.model.User;
import ar.edu.itba.webapp.form.UserForm;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        mav.addObject("username", name);
        return mav;
    }


    @ModelAttribute("userId")
    public Integer loggedUser(final HttpSession session)
    {
        return 1;//(Integer) session.getAttribute("LOGGED_USER_ID");
    }
}