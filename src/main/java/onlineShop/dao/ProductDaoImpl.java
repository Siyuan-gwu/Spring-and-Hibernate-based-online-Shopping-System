package onlineShop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Product getProductById(int productId) {
		// TODO Auto-generated method stub
		
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Product product = session.get(Product.class, productId);
			session.getTransaction().commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Product product = session.get(Product.class, productId);
			session.delete(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(product);
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
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
			Root<Product> root = criteriaQuery.from(Product.class);
			
			criteriaQuery.select(root);
			products = session.createQuery(criteriaQuery).getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

}
