package onlineShop.service;

import java.io.IOException;

import onlineShop.model.Cart;

public interface CartService {

	Cart getCartById(int cartId);
	
	Cart validate(int cartId) throws IOException;
	
	void updatePrice(int cartId);
}
