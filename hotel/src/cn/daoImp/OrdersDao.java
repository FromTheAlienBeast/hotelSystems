package cn.daoImp;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.dao.IOrdersDao;
import cn.etity.DinnerTable;
import cn.etity.Food;
import cn.etity.Orders;
import cn.utils.JdbcUtils;

public class OrdersDao implements IOrdersDao{

	public void save(Orders orders) {
		try{
			String sql="insert into Orders(table_id,orderDate,totalPrice,orderStatus," +
					"foodName,price,numbers) values(?,?,?,?,?,?,?)";
			System.out.println("totalPrice="+orders.getTotalPrice());
			JdbcUtils.getQueryRunner().update(sql,
					orders.getTable_id(),orders.getOrderDate(),
					orders.getTotalPrice(),orders.getOrderStatus(),
					orders.getFoodName(),orders.getPrice(),orders.getNumbers());
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}

	public void delete(int id) {
		String sql="delete from Orders where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	//订单的更新
	public void update(Orders orders) {
		String sql="update Orders set numbers=? totalPrice=? where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,orders.getNumbers(),orders.getTotalPrice(),orders.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Orders> select() {
		String sql="select * from Orders";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Orders>(Orders.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	//计算order的总价格
	public double total() {
		String sql="select sum(totalPrice) from Orders";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new ScalarHandler(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public double selectXQ(DinnerTable dinnertable) {
		
		String sql="select * from Orders where table_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new ScalarHandler(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
