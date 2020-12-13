package com.mvc.service;

import com.mvc.entity.UserEntity;

public interface IAccountService extends GeneralService<UserEntity> {
    public UserEntity findOneById(long id);

}
