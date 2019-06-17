package cn.service;

import java.util.List;

import cn.etity.Food;
import cn.utils.PageBean;

public interface IFoodService {

	public Food getFoodById(int id);
	
	public List<Food> getAllFood();
	/**
	 * 主键查询
	 */
	Food findById(int id);

	/**
	 * 分页查询
	 */
	void getAll(PageBean<Food> pb);
	
	public void save(Food food);
	
	void delete(int id);
	
	void update(Food food);
}
