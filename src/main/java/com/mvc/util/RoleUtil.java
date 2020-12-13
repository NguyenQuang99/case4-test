package com.mvc.util;

import com.mvc.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RoleUtil {

    @Autowired
    private static IAccountService accountService;

    public static boolean checkStatus(HttpServletRequest request, HttpServletResponse response) {
        if (SecurityUtils.getAuthorities().get(0).equals("ROLE_MANAGER") || SecurityUtils.getAuthorities().get(0).equals("ROLE_EMPLOYEE")) {
            if (!accountService.findOneById(SecurityUtils.getPrincipal().getId()).isActive()) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                    return true;
                }
            }
        }
        return false;
    }

    public  static List<Object> checkRole() {
        List<Object> listAuthor = new ArrayList<>();
        if (SecurityUtils.getAuthorities().get(0).equals("ROLE_MANAGER") || SecurityUtils.getAuthorities().get(0).equals("ROLE_EMPLOYEE")) {
           String fullName = SecurityUtils.getPrincipal().getUsername();
           listAuthor.add(fullName);
           String author = SecurityUtils.getAuthorities().get(0);
           listAuthor.add(author);
           Long userId = SecurityUtils.getPrincipal().getId();
           listAuthor.add(userId);
           String image = SecurityUtils.getPrincipal().getImage();
           listAuthor.add(image);
        }
        return listAuthor;
    }

}
