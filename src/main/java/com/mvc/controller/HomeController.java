package com.mvc.controller;

import com.mvc.dto.NewDTO;
import com.mvc.service.IAccountService;
import com.mvc.service.INewService;
import com.mvc.util.RoleUtil;
import com.mvc.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    @Autowired
    private IAccountService accountService;

//    @Autowired
//    private CommentService commentService;

    @RequestMapping(value = "/trang-chu")
    public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {

            ModelAndView mav = new ModelAndView("blog");

        NewDTO model = new NewDTO();
        model.setListResult(newService.findAll());
        if(RoleUtil.checkRole().size() > 0) {
            mav.addObject("fullName", RoleUtil.checkRole().get(0));
            mav.addObject("author", RoleUtil.checkRole().get(1));
            mav.addObject("userId",RoleUtil.checkRole().get(2));
            mav.addObject("image", RoleUtil.checkRole().get(3));
            if(!accountService.findOneById(SecurityUtils.getPrincipal().getId()).isActive()) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                    return new ModelAndView("redirect:/trang-chu");
                }
            }
        }
       mav.addObject("model", model);

       return mav;

    }

    @RequestMapping(value = "/trang-chu/{code}", method = RequestMethod.GET)
    public ModelAndView sortNew(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("blog");
        NewDTO model = new NewDTO();
        if(RoleUtil.checkRole().size() > 0) {
            mav.addObject("fullName", RoleUtil.checkRole().get(0));
            mav.addObject("author", RoleUtil.checkRole().get(1));
            mav.addObject("userId",RoleUtil.checkRole().get(2));
            mav.addObject("image", RoleUtil.checkRole().get(3));
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
    @RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
    public ModelAndView signUp(){
        ModelAndView mav = new ModelAndView("sign_up");
        return mav;
    }
    @RequestMapping(value = "/Contact_us", method = RequestMethod.GET)
    public ModelAndView contact(){
        ModelAndView mav = new ModelAndView("Contact_us");
        return mav;
    }
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public ModelAndView single(){
        ModelAndView mav = new ModelAndView("single");
        return mav;
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
    @RequestMapping(value = "/chuyen_la", method = RequestMethod.GET)
    public ModelAndView chuyen_la(){
        ModelAndView mav = new ModelAndView("chuyen_la");
        return mav;
    }
    @RequestMapping(value = "/doi_song", method = RequestMethod.GET)
    public ModelAndView doi_song(){
        ModelAndView mav = new ModelAndView("doi_song");
        return mav;
    }
    @RequestMapping(value = "/gia_dinh", method = RequestMethod.GET)
    public ModelAndView gia_dinh(){
        ModelAndView mav = new ModelAndView("gia_dinh");
        return mav;
    }
    @RequestMapping(value = "/kinh_te", method = RequestMethod.GET)
    public ModelAndView kinh_te(){
        ModelAndView mav = new ModelAndView("kinh_te");
        return mav;
    }
    @RequestMapping(value = "/quan_su", method = RequestMethod.GET)
    public ModelAndView quan_su(){
        ModelAndView mav = new ModelAndView("quan_su");
        return mav;
    }
    @RequestMapping(value = "/the_thao", method = RequestMethod.GET)
    public ModelAndView the_thao(){
        ModelAndView mav = new ModelAndView("the_thao");
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

