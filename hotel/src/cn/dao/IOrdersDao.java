package cn.dao;

import java.util.List;

import cn.etity.DinnerTable;
import cn.etity.Food;
import cn.etity.FoodType;
import cn.etity.Orders;

public interface IOrdersDao {

	//添加数据
	void save(Orders orders);
	
	void delete(int id);
	
	void update(Orders orders );
	//查询所有的订单
	List<Orders> select();
	//计算总价格
	double total();
	
	//根据餐桌查询订单
	double selectXQ(DinnerTable dinnertable);
}
