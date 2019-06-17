package cn.service;

import java.util.List;

import cn.etity.Food;
import cn.etity.Orders;

public interface IOrdersService {

	public void save(Orders orders);
	
	void delete(int id);
	
	void update(Orders orders);
	List<Orders> select();
	
	double total();
}
