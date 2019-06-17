package cn.serviceImp;

import java.util.List;

import cn.dao.IFoodTypeDao;
import cn.daoImp.FoodTypeDao;
import cn.etity.FoodType;
import cn.instance.BeanFactor;
import cn.service.IFoodTypeService;

public class FoodTypeService implements IFoodTypeService{

	//调用dao
	//private IFoodTypeDao foodTypeDao=new FoodTypeDao();
	//希望对象的创建不被写死，交给spring处理 或者 通过新建一个properties配置文件 
	private IFoodTypeDao foodTypeDao=BeanFactor.getInstance("foodTypeDao", IFoodTypeDao.class);
	
	public void save(FoodType foodType) {
		try{
			foodTypeDao.save(foodType);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		
		
	}

	public void update(FoodType foodType) {
		try{
		 foodTypeDao.update(foodType);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		try{
		foodTypeDao.delete(id);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public FoodType findById(int id) {
		try{
	
		return foodTypeDao.findById(id);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public List<FoodType> getAll() {
		
		try{
		return foodTypeDao.getAll();
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public List<FoodType> getAll(String typeName) {
		try{
		return foodTypeDao.getAll(typeName);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
