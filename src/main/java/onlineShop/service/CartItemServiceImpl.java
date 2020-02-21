package onlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineShop.dao.CartItemDao;
import onlineShop.model.Cart;
import onlineShop.model.CartItem;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemDao cartItemDao;
	
	public void addCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		cartItemDao.addCartItem(cartItem);
	}

	public void removeCartItem(int cartItemId) {
		// TODO Auto-generated method stub
		cartItemDao.removeCartItem(cartItemId);
	}

	public void removeAllCartItems(Cart cart) {
		// TODO Auto-generated method stub
		cartItemDao.removeAllCartItems(cart);
	}

}
