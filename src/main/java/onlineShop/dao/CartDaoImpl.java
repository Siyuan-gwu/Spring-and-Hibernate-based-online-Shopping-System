package onlineShop.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Cart;
import onlineShop.model.CartItem;

@Repository
public class CartDaoImpl implements CartDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Cart getCartById(int cartId) {
		// TODO Auto-generated method stub
		Cart cart = null;
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			cart = (Cart)session.get(Cart.class, cartId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cart;
	}
	
	@Override
	public void updatePrice(int cartId) {
		// TODO Auto-generated method stub
		Cart cart = getCartById(cartId);
		update(cart);
	}
	

	@Override
	public Cart validate(int cartId) throws IOException {
		// TODO Auto-generated method stub
		
		Cart cart = getCartById(cartId);
		if (cart == null || cart.getCartItem().size() == 0) {
			throw new IOException(cartId + "");
		}
		update(cart);
		return cart;
	}
	
	private void update(Cart cart) {
		int cartId = cart.getId();
		Double totalPrice = getSalesOrderTotal(cartId);
		cart.setTotalPrice(totalPrice);
		
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(cart);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private double getSalesOrderTotal(int cartId) {
		Cart cart = getCartById(cartId);
		double total = 0;
		List<CartItem> items = cart.getCartItem();
		for (CartItem item : items) {
			total += item.getPrice();
		}
		return total;
	}

}
