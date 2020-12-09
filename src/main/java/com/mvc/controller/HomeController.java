package com.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @RequestMapping(value = "/trang-chu")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }
}

