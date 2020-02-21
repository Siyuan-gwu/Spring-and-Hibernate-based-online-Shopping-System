package onlineShop.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Authorities;
import onlineShop.model.Cart;
import onlineShop.model.Customer;
import onlineShop.model.User;

/**
 * 
 * @author zhangsiyuan
 *	repository: create a instance, put into container while spring start
 *	Autowired: get a instance from container
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		User user = null;
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("emailId"), customer.getUser().getEmailId()));
			user = session.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			return false;
		}
		customer.getUser().setEnabled(true);
		
		Cart cart = new Cart();
		cart.setCustomer(customer);
		customer.setCart(cart);
		
		Authorities authorities = new Authorities();
		authorities.setAuthorities("ROLE_USER");
		authorities.setEmailId(customer.getUser().getEmailId());
		try {
			session.beginTransaction();
			session.save(authorities);
			session.save(customer);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
		return true;
	}

	public Customer getCustomerByUserName(String username) {
		// TODO Auto-generated method stub
		User user = null;
		
		try (Session session = sessionFactory.openSession()) {
			/**
			 * query in database
			 */
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("emailId"), username));
			user = session.createQuery(criteriaQuery).getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			return user.getCustomer();
		}
		return null;
	}

}
