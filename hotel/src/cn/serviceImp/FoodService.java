package cn.serviceImp;

import java.util.List;

import cn.dao.IFoodDao;
import cn.etity.Food;
import cn.instance.BeanFactor;
import cn.service.IFoodService;
import cn.utils.PageBean;

public class FoodService implements IFoodService{

	private IFoodDao foodDao=BeanFactor.getInstance("foodDao", IFoodDao.class);
	public List<Food> getAllFood() {
		try{
			return foodDao.getAllFood();
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}
	public Food findById(int id) {
		try {
			return foodDao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	public void getAll(PageBean<Food> pb) {
		try {
			foodDao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Food getFoodById(int id) {
		try {
			return foodDao.getFoodById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void save(Food food) {
		try {
			foodDao.save(food);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(int id) {
		try {
			foodDao.delete(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Food food) {
		try {
			foodDao.update(food);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
