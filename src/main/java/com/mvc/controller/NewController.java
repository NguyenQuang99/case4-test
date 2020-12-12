package com.mvc.controller;

import com.mvc.dto.CommentDTO;
import com.mvc.dto.NewDTO;
import com.mvc.service.INewService;
import com.mvc.service.impl.CommentService;
import com.mvc.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "newControllerOfWeb")
public class NewController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private INewService newService;





    @RequestMapping(value = "/trang-chu/noi-dung/{id}", method = RequestMethod.GET)
//    public ModelAndView detail() {
//        ModelAndView mav = new ModelAndView("web/new/detail");
//        CommentDTO model = new CommentDTO();
//        long id = 1;
//        model.setListResult(commentService.getAllByNewId(id));
//        System.out.println(model.getListResult());
//        mav.addObject("model", model);
//        return mav;
//    }

    public ModelAndView detail(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("detail");
        CommentDTO model = new CommentDTO();
        NewDTO newDTO = newService.findById(id);
        mav.addObject("newDTO", newDTO);
        model.setListResult(commentService.getAllByNewId(id));
        if(SecurityUtils.getAuthorities().get(0).equals("ROLE_MANAGER") || SecurityUtils.getAuthorities().get(0).equals("ROLE_EMPLOYEE")) {
            mav.addObject("fullName", SecurityUtils.getPrincipal().getUsername());
            mav.addObject("userId", SecurityUtils.getPrincipal().getId());
        }
        mav.addObject("model", model);
        return mav;

    }
}

