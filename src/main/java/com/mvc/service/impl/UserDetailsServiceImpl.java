package com.mvc.service.impl;

import com.mvc.dto.MyUser;
import com.mvc.entity.UserEntity;
import com.mvc.repository.UserRepository;
import com.mvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements IUserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(username);
        System.out.println("Account= " + user);

        if (user == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }

        // EMPLOYEE,MANAGER,..
        String role = user.getUserRole();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        // ROLE_EMPLOYEE, ROLE_MANAGER
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        grantList.add(authority);

        boolean enabled = user.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        MyUser myUser = new MyUser(user.getUserName(), user.getEncrytedPassword(),true, true, true, true, grantList);

//        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUserName(), //
//                user.getEncrytedPassword(), enabled, accountNonExpired, //
//                credentialsNonExpired, accountNonLocked, grantList);
        myUser.setId(user.getId());
        myUser.setImage(user.getImage());
        return myUser;
    }




    @Override
    public UserEntity findByUsername(String username) {
        UserEntity user = userRepository.findByUserName(username);
        return user;
    }
}