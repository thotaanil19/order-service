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

import com.treez.orderservice.domain.Order;
import com.treez.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@PostMapping
	public Order save(@RequestBody Order order) {
		LOGGER.debug("Saving Order: ", order);
		return orderService.save(order);
	}

	@GetMapping
	public List<Order> getAllOrders() {
		LOGGER.debug("Getting All Orders");
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") int id) {
		LOGGER.debug("Getting Order by Id : ", id);
		Order order = orderService.getOrderById(id);
		HttpStatus httpStatus = order != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Order>(order, httpStatus);
	}

	@PutMapping
	public Order update(@RequestBody Order order) {
		LOGGER.debug("Updating Order", order);
		return orderService.updateOrder(order);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		LOGGER.debug("Deleting Order by Id: ", id);
		boolean deleteStatus = orderService.deleteOrder(id);
		String message = deleteStatus ? "Order Deleted" : "Order Not Deleted";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
