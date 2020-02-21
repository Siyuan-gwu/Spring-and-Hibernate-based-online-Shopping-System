package onlineShop.dao;

import onlineShop.model.SalesOrder;

public interface SalesOrderDao {
	
	void addSalesOrder(SalesOrder salesOrder);
	
	void removeSalesOrder(int salesOrderId);
	
	int getSalesOrder(int salesOrderId);

}
