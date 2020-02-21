package onlineShop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.SalesOrder;

@Repository
public class SalesOrderDaoImpl implements SalesOrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addSalesOrder(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(salesOrder);
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
	public void removeSalesOrder(int salesOrderId) {
		// TODO Auto-generated method stub
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			SalesOrder salesOrder = session.get(SalesOrder.class, salesOrderId);
			session.beginTransaction();
			session.delete(salesOrder);
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
	public int getSalesOrder(int salesOrderId) {
		// TODO Auto-generated method stub
		return salesOrderId;
	}

}
