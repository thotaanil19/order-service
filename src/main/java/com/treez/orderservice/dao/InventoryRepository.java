package com.treez.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treez.orderservice.domain.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
