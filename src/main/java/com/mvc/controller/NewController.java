package com.mvc.controller;

import com.mvc.dto.CommentDTO;
import com.mvc.dto.NewDTO;
import com.mvc.service.IAccountService;
import com.mvc.service.INewService;
import com.mvc.service.impl.CommentService;
import com.mvc.util.RoleUtil;
import com.mvc.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(value = "newControllerOfWeb")
public class NewController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private INewService newService;

    @Autowired
    private IAccountService accountService;



    @RequestMapping(value = "/trang-chu/noi-dung/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("detail");
        CommentDTO model = new CommentDTO();
        NewDTO newDTO = newService.findById(id);
        mav.addObject("newDTO", newDTO);
        model.setListResult(commentService.getAllByNewId(id));
        if(RoleUtil.checkRole().size() > 0) {
            mav.addObject("fullName", RoleUtil.checkRole().get(0));
            mav.addObject("author", RoleUtil.checkRole().get(1));
            mav.addObject("userId",RoleUtil.checkRole().get(2));
        }
        mav.addObject("model", model);
        return mav;

    }
}

