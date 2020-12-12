package com.mvc.controller;

import com.mvc.entity.UserEntity;
import com.mvc.entity.UserEntityForm;
import com.mvc.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tai-khoan")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<UserEntity> userEntities = (List<UserEntity>) accountService.findAll();
        modelAndView.addObject("userEntitys", userEntities);
        modelAndView.addObject("message", "Thanh cong");
        return modelAndView;
    }

    @GetMapping("/tao-moi")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("sign_up");
        modelAndView.addObject("userEntity", new UserEntityForm());
        return modelAndView;
    }

    @PostMapping("/tao-moi")
    public RedirectView createTask(@ModelAttribute UserEntityForm userEntity) {
        UserEntity userEntity1 = new UserEntity.UserEntityBuilder(userEntity.getUserName())
                .encrytedPassword(userEntity.getEncrytedPassword())
                .userRole(userEntity.getUserRole())
                .active(userEntity.isActive()).build();
        MultipartFile multipartFile = userEntity.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(userEntity.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String password = BCrypt.hashpw(userEntity.getEncrytedPassword(), BCrypt.gensalt(10));
        userEntity1.setEncrytedPassword(password);

        userEntity1.setImage(fileName);

        accountService.save(userEntity1);
        return new RedirectView("");

    }

}
