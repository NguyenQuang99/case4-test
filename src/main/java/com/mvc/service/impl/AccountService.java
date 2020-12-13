package com.mvc.service.impl;

import com.mvc.entity.UserEntity;
import com.mvc.repository.UserRepository;
import com.mvc.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserEntity findOneById(long id) {
        return userRepository.findOneById(id);
    }
}
