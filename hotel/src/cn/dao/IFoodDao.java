package cn.dao;

import java.util.List;

import cn.etity.DinnerTable;
import cn.etity.Food;
import cn.utils.PageBean;

public interface IFoodDao {

	//根据主键查询菜品
	public Food getFoodById(int id);
	//查询所有的菜品
	public List<Food> getAllFood();
	
	/**
	 * 分页且按条件查询所有的菜品
	 */
	void getAll(PageBean<Food> pb);
	
	/**
	 * 按条件统计菜品的总记录数
	 */
	int getTotalCount(PageBean<Food> pb);
	
	/**
	 * 根据id查询
	 * 连接查询
	 */
	Food findById(int id);
	//保存food
	public void save(Food food);
	
	void delete(int id);
	
	void update(Food food);
}
