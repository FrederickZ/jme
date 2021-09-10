package com.frederickz.jme.repository;

import com.frederickz.jme.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
