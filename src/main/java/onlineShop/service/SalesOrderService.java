package onlineShop.service;

import onlineShop.model.SalesOrder;

public interface SalesOrderService {

	void addSalesOrder(SalesOrder salesOrder);
	
	void removeSalesOrder(int salesOrderId);
	
	int getSalesOrder(int salesOrderId);
}
