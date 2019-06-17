package cn.serviceImp;

import java.util.List;

import cn.dao.IOrdersDao;
import cn.etity.Orders;
import cn.instance.BeanFactor;
import cn.service.IOrdersService;

public class OrdersService implements IOrdersService{

	private IOrdersDao ordersDao=BeanFactor.getInstance("ordersDao", IOrdersDao.class);
	public void save(Orders orders) {
		try{
			ordersDao.save(orders);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void delete(int id) {
		try{
			ordersDao.delete(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void update(Orders orders) {
		try{
			ordersDao.update(orders);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<Orders> select() {
		try{
			return ordersDao.select();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public double total() {
		try{
			return ordersDao.total();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
