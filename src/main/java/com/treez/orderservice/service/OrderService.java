package com.treez.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.treez.orderservice.dao.OrderRepository;
import com.treez.orderservice.domain.Order;

@Service
public class OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOrderById(Integer id) {
		Order order = null;
		Optional<Order> obj = orderRepository.findById(id);
		if (obj.isPresent()) {
			order = obj.get();
		}
		return order;
	}

	public Order updateOrder(Order order) {
		Order orderInDB = getOrderById(order.getId());

		if (order.getCustomerEmail() != null) {
			orderInDB.setCustomerEmail(order.getCustomerEmail());
		}

		if (order.getOrderPlacedDate() != null) {
			orderInDB.setOrderPlacedDate(order.getOrderPlacedDate());
		}

		if (order.getOrderStatus() != null) {
			orderInDB.setOrderStatus(order.getOrderStatus());
		}

		return save(orderInDB);
	}

	public boolean deleteOrder(int id) {
		try {
			orderRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.warn("Order not exists with id : " + id);
			return false;
		}
	}

}
