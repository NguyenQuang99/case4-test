package com.mvc.controller;

import com.mvc.dto.NewDTO;
import com.mvc.service.IAccountService;
import com.mvc.service.ICategoryService;
import com.mvc.service.INewService;
import com.mvc.service.IUserService;
import com.mvc.util.MessageUtil;
import com.mvc.util.RoleUtil;
import com.mvc.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.attribute.UserPrincipal;
import java.util.Map;

@Controller(value = "newControllerOfAdmin")
public class AdminNewController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;


    @RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpServletRequest request, HttpServletResponse response) {
//    public ModelAndView showList( HttpServletRequest request) {
        NewDTO model = new NewDTO();
        model.setPage(page);
        model.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin_list");

//        Pageable pageable = new PageRequest(page - 1, limit, Sort.by("id").descending()) ;
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id"));
        model.setListResult(newService.findAll(pageable));
//        model.setListResult(newService.findAll());
        model.setTotalItem(newService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        if(RoleUtil.checkRole().size() > 0) {
            mav.addObject("fullName", RoleUtil.checkRole().get(0));
            mav.addObject("author", RoleUtil.checkRole().get(1));
            mav.addObject("userId",RoleUtil.checkRole().get(2));
            mav.addObject("image", RoleUtil.checkRole().get(3));
        }
        mav.addObject("model", model);
        return mav;
    }


    @RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin_edit");
        NewDTO model = new NewDTO();
        if (id != null) {
            model = newService.findById(id);
        }
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("model", model );
        if(RoleUtil.checkRole().size() > 0) {
            mav.addObject("fullName", RoleUtil.checkRole().get(0));
            mav.addObject("author", RoleUtil.checkRole().get(1));
            mav.addObject("userId",RoleUtil.checkRole().get(2));
            mav.addObject("image", RoleUtil.checkRole().get(3));
        }
        return mav;
    }


}
