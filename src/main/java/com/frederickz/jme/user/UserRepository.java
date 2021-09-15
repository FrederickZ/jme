package com.frederickz.jme.user;

import com.frederickz.jme.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
