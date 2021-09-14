package com.frederickz.jme.user.service;

import com.frederickz.jme.infrastructure.CrudService;
import com.frederickz.jme.user.domain.User;

public interface UserService extends CrudService<User, Long> {

    long count();

}
