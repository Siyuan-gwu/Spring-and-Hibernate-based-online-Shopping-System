package onlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineShop.dao.SalesOrderDao;
import onlineShop.model.SalesOrder;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@Override
	public void addSalesOrder(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		salesOrderDao.addSalesOrder(salesOrder);

	}
	
	@Override
	public void removeSalesOrder(int salesOrderId) {
		// TODO Auto-generated method stub
		salesOrderDao.removeSalesOrder(salesOrderId);
	}
	
	@Override
	public int getSalesOrder(int salesOrderId) {
		// TODO Auto-generated method stub
		return salesOrderDao.getSalesOrder(salesOrderId);
	}
}
