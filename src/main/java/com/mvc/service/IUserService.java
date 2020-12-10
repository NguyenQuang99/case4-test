package com.mvc.service;

import com.mvc.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{
        UserEntity findByUsername(String username);
}
