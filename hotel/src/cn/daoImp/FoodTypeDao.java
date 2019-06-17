package cn.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.dao.IFoodTypeDao;
import cn.etity.FoodType;
import cn.utils.JdbcUtils;
public class FoodTypeDao implements IFoodTypeDao{

	public void save(FoodType foodType) {
		
		String sql="insert into FoodType(typeName) values(?);";
		
		try { 
			JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void update(FoodType foodType) {
		// TODO Auto-generated method stub
		String sql="update FoodType set typeName=? where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName(),foodType.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from FoodType where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public FoodType findById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from FoodType where id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<FoodType>(FoodType.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public List<FoodType> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from FoodType";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<FoodType> getAll(String typeName) {
		// TODO Auto-generated method stub
		String sql="select * from foodType where typeName like ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class),"%"+typeName+"%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	
}
