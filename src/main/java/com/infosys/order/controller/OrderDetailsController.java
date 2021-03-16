package com.infosys.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.order.dto.CartDTO;
import com.infosys.order.dto.OrderDetailsDTO;
import com.infosys.order.dto.ProductsOrderedDTO;
import com.infosys.order.entity.CompositeKey;

import com.infosys.order.service.OrderDetailsService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class OrderDetailsController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderDetailsService orderDetailsService;

	@Value("${cart.uri}")
	String cartUri;

	//1.  Get details of a specific order
	@GetMapping(value = "/orders/{orderid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDetailsDTO getOrderDetailsByOrderId(@PathVariable int orderid) {
		logger.info("Fetching order details of specific orderid {}", orderid);
		return orderDetailsService.getOrderDetailsByOrderId(orderid);
	}

	//2.  Get details of a All order
	@GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDetailsDTO> getAllOrderDetails() {
		logger.info("get all Orderdetails");

		return orderDetailsService.getAllOrderDetails();
	}
	
	//3.  adding product to cart through rest template
		@PostMapping(value = "/api/cart/add", produces = MediaType.APPLICATION_JSON_VALUE)
		public void AddToCart(@RequestBody CartDTO cartDTO) {
			logger.info("adding product to cart :{}", cartDTO);
			String url = "http://localhost:8100/api/cart/add";
			RestTemplate restTemp = new RestTemplate();
			String str = restTemp.postForObject(url, cartDTO, String.class);
			System.out.println(str);
		}
	//4. deleting the product from cart through rest template
		@DeleteMapping(value = "/cart/remove/{buyerid}/{prodid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public void removeCartItems(@PathVariable int buyerid, @PathVariable int prodid) {
			String url = "http://localhost:8100/api/cart/remove";
			logger.info("Detching details of cart {}", buyerid, prodid);
			new RestTemplate().delete(url + "/" + buyerid + "/" + prodid);
		}
		
		
		
		
		//extra endpoints 

	// add Order details
	@PostMapping(value = "/orders")
	public ResponseEntity<String> saveOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
		orderDetailsService.saveOrderDetails(orderDetailsDTO);
		return new ResponseEntity<String>("order added successfully", HttpStatus.OK);
	}

	// add products
	@PostMapping(value = "/products")
	public ResponseEntity<String> saveProductOrderDetails(@RequestBody ProductsOrderedDTO productsOrderedDTO) {
		orderDetailsService.saveProductOrderDetails(productsOrderedDTO);
		return new ResponseEntity<String>("product order added successfully", HttpStatus.OK);

	}

	@DeleteMapping(value = "/orders/{orderid}")
	public ResponseEntity<String> removeOrderDetails(@PathVariable int orderid) {
		String response= orderDetailsService.removeOrderDetails(orderid);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/orders/{orderid}/{prodid}")
	public ResponseEntity<String> removeProductOrderDetails(@PathVariable int orderid, @PathVariable int prodid) {
		CompositeKey compositeKey = new CompositeKey();
		compositeKey.setOrderid(orderid);
		compositeKey.setProdid(prodid);
		orderDetailsService.removeProductOrderDetails(compositeKey);
		return new ResponseEntity<String>("product order deleted successfully", HttpStatus.OK);
	}

	

}
