package com.treez.orderservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treez.orderservice.domain.Inventory;
import com.treez.orderservice.service.InventoryService;

@RestController
@RequestMapping("/inventories")
public class InventoriesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoriesController.class);

	@Autowired
	private InventoryService inventoryService;

	@PostMapping
	public Inventory save(@RequestBody Inventory inventory) {
		LOGGER.debug("Saving Inventory", inventory);
		return inventoryService.save(inventory);

	}

	@GetMapping
	public List<Inventory> getAllInventories() {
		LOGGER.debug("Getting All Inventories");
		return inventoryService.getAllInventories();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getInventoryById(@PathVariable("id") int id) {
		LOGGER.debug("Getting Inventory by Id: " + id);
		Inventory inventory = inventoryService.getInventoryById(id);
		HttpStatus httpStatus = inventory != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Inventory>(inventory, httpStatus);
	}

	@PutMapping
	public Inventory update(@RequestBody Inventory inventory) {
		LOGGER.debug("Update Inventory", inventory);
		return inventoryService.updateInventory(inventory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		LOGGER.debug("Delete Inventory by Id: " + id);
		boolean deleteStatus = inventoryService.deleteInventory(id);
		String message = deleteStatus ? "Inventoty Deleted" : "Inventoty Not Deleted";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
