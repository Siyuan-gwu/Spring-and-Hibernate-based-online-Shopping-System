package onlineShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Cart;
import onlineShop.model.CartItem;

@Repository
public class CartItemDaoImpl implements CartItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		Session session = null;
		Cart cart = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			cart = cartItem.getCart();
			cart.setTotalPrice(cart.getTotalPrice()+cartItem.getPrice());
			session.saveOrUpdate(cartItem);
			//update the totalPrice in Cart table
			session.saveOrUpdate(cart);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void removeCartItem(int cartItemId) {
		// TODO Auto-generated method stub
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			CartItem cartItem = session.get(CartItem.class, cartItemId);
			Cart cart = cartItem.getCart();
			List<CartItem> cartItems = cart.getCartItem();
			cartItems.remove(cartItem);
			cart.setTotalPrice(cart.getTotalPrice() - cartItem.getPrice());
			session.beginTransaction();
			session.delete(cartItem);
			//update the totalPrice in Cart table
			session.saveOrUpdate(cart);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void removeAllCartItems(Cart cart) {
		// TODO Auto-generated method stub
		List<CartItem> cartItems = cart.getCartItem();
		for (CartItem item : cartItems) {
			removeCartItem(item.getId());
		}

	}

}
