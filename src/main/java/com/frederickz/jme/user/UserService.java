package com.frederickz.jme.user;

import com.frederickz.jme.infrastructure.CrudService;
import com.frederickz.jme.user.User;

public interface UserService extends CrudService<User, Long> {

    long count();

}
