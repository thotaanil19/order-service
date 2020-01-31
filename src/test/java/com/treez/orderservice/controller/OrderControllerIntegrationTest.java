package com.treez.orderservice.controller;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.treez.orderservice.domain.Order;

@SpringBootTest
public class OrderControllerIntegrationTest {

	@Autowired
	private OrderController orderController;

	private Order order = null;

	@BeforeEach
	public void setUp() {
		order = new Order();
		order.setCustomerEmail("Test@gmail.com");
		order.setOrderStatus("Test");
		order.setOrderPlacedDate(new Date());
		order = orderController.save(order);
	}

	@AfterEach
	public void tearDown() {
		orderController.deleteById(order.getId());
	}

	@Test
	public void testGetAllOrders() {
		List<Order> actual = orderController.getAllOrders();
		Assertions.assertNotNull(actual);
	}

	@Test
	public void testGetInventoryById() {
		ResponseEntity<?> actual = orderController.getOrderById(order.getId());
		Assertions.assertNotNull(actual);
		Assertions.assertNotNull(actual.getBody());
		Assertions.assertEquals(order.getId(), ((Order) actual.getBody()).getId());
	}

	@Test
	public void testUpdate() {
		order.setCustomerEmail("test1@gmail.com");
		Order actual = orderController.update(order);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(order.getCustomerEmail(), actual.getCustomerEmail());
	}

}
