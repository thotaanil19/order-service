package com.treez.orderservice.controller;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.treez.orderservice.domain.Inventory;

@SpringBootTest
public class InventoryControllerIntegrationTest {

	@Autowired
	private InventoryController inventoriesController;

	private Inventory inventory = null;

	@BeforeEach
	public void setUp() {
		inventory = new Inventory();
		inventory.setDescription("Test");
		inventory.setName("Test");
		inventory.setPrice(1F);
		inventory.setQuantityAvailable(1);
		inventory = inventoriesController.save(inventory);
	}

	@AfterEach
	public void tearDown() {
		inventoriesController.deleteById(inventory.getId());
	}

	@Test
	public void testGetAllInventories() {
		List<Inventory> actual = inventoriesController.getAllInventories();
		Assertions.assertNotNull(actual);
	}

	@Test
	public void testGetInventoryById() {
		ResponseEntity<?> actual = inventoriesController.getInventoryById(inventory.getId());
		Assertions.assertNotNull(actual);
		Assertions.assertNotNull(actual.getBody());
		Assertions.assertEquals(inventory.getId(), ((Inventory) actual.getBody()).getId());
	}

	@Test
	public void testUpdate() {
		inventory.setDescription("test1");
		Inventory actual = inventoriesController.update(inventory);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(inventory.getDescription(), actual.getDescription());
	}

}
