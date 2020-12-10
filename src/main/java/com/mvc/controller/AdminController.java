package com.mvc.controller;


import com.mvc.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfAdmin")
public class AdminController {
    @RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin_home");
        if(SecurityUtils.getAuthorities().get(0).equals("ROLE_MANAGER") || SecurityUtils.getAuthorities().get(0).equals("ROLE_EMPLOYEE")) {
            mav.addObject("fullName", SecurityUtils.getPrincipal().getUsername());
        }
        return mav;
    }
}
