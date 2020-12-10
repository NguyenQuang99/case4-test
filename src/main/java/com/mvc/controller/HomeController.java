package com.mvc.controller;

import com.mvc.dto.NewDTO;
import com.mvc.service.INewService;
import com.mvc.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController {

    @Autowired
    private INewService newService;

//    @Autowired
//    private CommentService commentService;

    @RequestMapping(value = "/trang-chu")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("home");
        NewDTO model = new NewDTO();
        model.setListResult(newService.findAll());
        if(SecurityUtils.getAuthorities().get(0).equals("ROLE_MANAGER") || SecurityUtils.getAuthorities().get(0).equals("ROLE_EMPLOYEE")) {
            mav.addObject("fullName", SecurityUtils.getPrincipal().getUsername());
            System.out.println(SecurityUtils.getPrincipal().getUsername());

        }
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/trang-chu/{code}", method = RequestMethod.GET)
    public ModelAndView sortNew(@PathVariable("code") String code) {
        ModelAndView mav = new ModelAndView("web/home");
        NewDTO model = new NewDTO();
        if(SecurityUtils.getAuthorities().get(0).equals("ROLE_MANAGER") || SecurityUtils.getAuthorities().get(0).equals("ROLE_EMPLOYEE")) {
            mav.addObject("fullName", SecurityUtils.getPrincipal().getUsername());
        }
        model.setListResult(newService.getAllByCategory(code));
        mav.addObject("model", model);
        return mav;

    }




    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/thoat", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/trang-chu");
    }


    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        return new ModelAndView("redirect:/dang-nhap?accessDenied");
    }
}

