package com.frederickz.jme.repository;

import com.frederickz.jme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
