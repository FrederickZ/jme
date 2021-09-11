package com.frederickz.jme.repository;

import com.frederickz.jme.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
