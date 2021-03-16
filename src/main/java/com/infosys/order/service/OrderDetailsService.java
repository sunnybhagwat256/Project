package com.infosys.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.order.dto.OrderDetailsDTO;
import com.infosys.order.dto.ProductsOrderedDTO;
import com.infosys.order.entity.CompositeKey;
import com.infosys.order.entity.OrderDetails;
import com.infosys.order.entity.ProductsOrdered;
import com.infosys.order.repository.OrderDetailsRepository;
import com.infosys.order.repository.ProductOrderedRepository;

@Service
public class OrderDetailsService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	OrderDetailsRepository orderDetailsRepo;
	@Autowired
	ProductOrderedRepository productsRepo;

	// get specific order details
	public OrderDetailsDTO getOrderDetailsByOrderId(int orderid) {
		OrderDetailsDTO orderDetailsDTO = null;

		Optional<OrderDetails> opt = orderDetailsRepo.findById(orderid);
		if (opt.isPresent()) {
			OrderDetails orderDetails = opt.get();
			List<ProductsOrderedDTO> listDTO = new ArrayList<ProductsOrderedDTO>();
			List<ProductsOrdered> productOrder = productsRepo.findByorderid(orderid);

			for (ProductsOrdered p : productOrder) {
				ProductsOrderedDTO products = ProductsOrderedDTO.valueOf(p);
				listDTO.add(products);
			}
			orderDetailsDTO = OrderDetailsDTO.valueOf(orderDetails, listDTO);
		}
		return orderDetailsDTO;
	}

	// get all order details
	public List<OrderDetailsDTO> getAllOrderDetails() {

		List<OrderDetails> orders = orderDetailsRepo.findAll();
		List<OrderDetailsDTO> orderDTOs = new ArrayList<OrderDetailsDTO>();

		for (OrderDetails o : orders) {
			OrderDetailsDTO allOrders = OrderDetailsDTO.valueOf(o, null);
			List<ProductsOrdered> products = productsRepo.findAll();
			List<ProductsOrderedDTO> productsDTO = new ArrayList<ProductsOrderedDTO>();
			for (ProductsOrdered p : products) {
				if (p.getOrderid() == o.getOrderid()) {
					ProductsOrderedDTO productDTO = ProductsOrderedDTO.valueOf(p);
					productsDTO.add(productDTO);
					System.out.println(p.getOrderid());
					System.out.println(o.getOrderid());
				}
			}
			allOrders.setOrderProducts(productsDTO);
			orderDTOs.add(allOrders);
		}
		return orderDTOs;

	}

	// Save order details
	public void saveOrderDetails(OrderDetailsDTO orderDetailsDTO) {
		logger.info("Saving Order details for:" + orderDetailsDTO.getOrderid());
		OrderDetails orderDetails = orderDetailsDTO.createEntity();
		orderDetailsRepo.save(orderDetails);

	}

	// save product details
	public void saveProductOrderDetails(ProductsOrderedDTO productsOrderedDTO) {
		logger.info("Saving Product Order details for:" + productsOrderedDTO.getProdid());
		ProductsOrdered productsOrdered = productsOrderedDTO.createEntity();
		productsRepo.save(productsOrdered);
	}

	// delete specific order details
	public String removeOrderDetails(int orderid) {
		logger.info("removing Order details for:" + orderid);
		OrderDetails orderDetails = orderDetailsRepo.findById(orderid).orElse(null);
		if (orderDetails != null) {
			orderDetailsRepo.deleteById(orderid);
			return "Product Removed";
		}
		return "Product Not Found";
	}

	// delete product details through orderid
	public void removeProductOrderDetails(CompositeKey compositeKey) {
		logger.info("removing Product Order details for:" + compositeKey.getProdid());
		ProductsOrdered productsOrdered = productsRepo.findById(compositeKey).orElse(null);
		if (productsOrdered != null) {
			productsRepo.deleteById(compositeKey);
		}

	}

}
