package com.frederickz.jme.user.repository;

import com.frederickz.jme.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
