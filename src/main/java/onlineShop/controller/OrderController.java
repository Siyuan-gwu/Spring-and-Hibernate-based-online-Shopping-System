package onlineShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import onlineShop.model.Cart;
import onlineShop.model.Customer;
import onlineShop.model.SalesOrder;
import onlineShop.service.CartService;
import onlineShop.service.SalesOrderService;

@Controller
public class OrderController {
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@Autowired
	private CartService cartService;
	
	
	@RequestMapping(value = "/order/{cartId}", method = RequestMethod.GET)
	public String createOrder(@PathVariable(value = "cartId") int cartId) {
		
		SalesOrder salesOrder = new SalesOrder();
		Cart cart = cartService.getCartById(cartId);
		salesOrder.setCart(cart);
		
		Customer customer = cart.getCustomer();
		salesOrder.setCustomer(customer);
		salesOrder.setShippingAddress(customer.getShippingAddress());
		salesOrder.setBillingAddress(customer.getBillingAddress());
		salesOrderService.addSalesOrder(salesOrder);
		System.out.println(salesOrder.getId());
		return "redirect:/checkout?cartId="+cartId+"&salesOrderId="+salesOrder.getId();
		
	}

}
