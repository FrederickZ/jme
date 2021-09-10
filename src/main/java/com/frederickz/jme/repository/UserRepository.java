package com.frederickz.jme.repository;

import com.frederickz.jme.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
