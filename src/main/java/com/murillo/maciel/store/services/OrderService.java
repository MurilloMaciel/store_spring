package com.murillo.maciel.store.services;

import com.murillo.maciel.store.domain.BankSlipPayment;
import com.murillo.maciel.store.domain.CardPayment;
import com.murillo.maciel.store.domain.Order;
import com.murillo.maciel.store.domain.OrderItem;
import com.murillo.maciel.store.domain.enums.PaymentStatus;
import com.murillo.maciel.store.repositories.OrderItemRepository;
import com.murillo.maciel.store.repositories.OrderRepository;
import com.murillo.maciel.store.repositories.PaymentRepository;
import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService
{
	@Autowired
	private OrderRepository repository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BankSlipService bankSlipService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order find(Integer id)
	{
		ObjNotFoundException e = new ObjNotFoundException("Object not found, id -> " + id + ", type -> " + Order.class.getName());
		Optional<Order> order = repository.findById(id);
		return order.orElseThrow( () -> e );
	}

	public Order insert(Order order)
	{
		order.setId(null);
		order.setInstant(new Date());
		order.getPayment().setPaymentStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);
		if (order.getPayment() instanceof BankSlipPayment)
		{
			BankSlipPayment payment = (BankSlipPayment) order.getPayment();
			bankSlipService.fillPayment(payment, order.getInstant());
		}
		else
		{
			CardPayment payment = (CardPayment) order.getPayment();
		}
		order = repository.save(order);
		paymentRepository.save(order.getPayment());
		for (OrderItem orderItem : order.getItens())
		{
			orderItem.setDiscount(0.0);
			orderItem.setPrice(productService.find(orderItem.getProduct().getId()).getPrice());
			orderItem.setOrder(order);
		}
		orderItemRepository.saveAll(order.getItens());
		return order;
	}
}
