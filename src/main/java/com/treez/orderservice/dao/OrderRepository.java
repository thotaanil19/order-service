package com.treez.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treez.orderservice.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
