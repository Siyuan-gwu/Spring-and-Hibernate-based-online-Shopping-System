package onlineShop.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineShop.dao.CartDao;
import onlineShop.model.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	@Override
	public Cart getCartById(int cartId) {
		// TODO Auto-generated method stub
		return cartDao.getCartById(cartId);
	}

	@Override
	public Cart validate(int cartId) throws IOException {
		// TODO Auto-generated method stub
		return cartDao.validate(cartId);
	}
	
	@Override
	public void updatePrice(int cartId) {
		// TODO Auto-generated method stub
		cartDao.updatePrice(cartId);
	}


}
