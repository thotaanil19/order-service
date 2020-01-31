package com.treez.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.treez.orderservice.dao.InventoryRepository;
import com.treez.orderservice.domain.Inventory;

@Service
public class InventoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

	@Autowired
	private InventoryRepository inventoryRepository;

	public Inventory save(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
	}

	public Inventory getInventoryById(int id) {
		Inventory inventory = null;
		Optional<Inventory> obj = inventoryRepository.findById(id);
		if (obj.isPresent()) {
			inventory = obj.get();
		}
		return inventory;
	}

	public Inventory updateInventory(Inventory inventory) {
		Inventory inventoryInDB = getInventoryById(inventory.getId());

		if (inventory.getName() != null) {
			inventoryInDB.setName(inventory.getName());
		}

		if (inventory.getDescription() != null) {
			inventoryInDB.setDescription(inventory.getDescription());
		}

		if (inventory.getPrice() != null) {
			inventoryInDB.setPrice(inventory.getPrice());
		}

		if (inventory.getQuantityAvailable() != null) {
			inventoryInDB.setQuantityAvailable(inventory.getQuantityAvailable());
		}

		return save(inventoryInDB);
	}

	public boolean deleteInventory(int id) {
		try {
			inventoryRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.warn("Inventory not exists with id : " + id);
			return false;
		}

	}

}
