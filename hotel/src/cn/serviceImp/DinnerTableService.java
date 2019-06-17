package cn.serviceImp;

import java.util.List;

import cn.dao.IDinnerTableDao;
import cn.etity.DinnerTable;
import cn.etity.TableStatus;
import cn.instance.BeanFactor;
import cn.service.IDinnerTableService;



public class DinnerTableService implements IDinnerTableService{

	private IDinnerTableDao dinnerTableDao=BeanFactor.getInstance("dinnerTableDao", IDinnerTableDao.class);
	
	public List<DinnerTable> findByNoUse() {
		try{
			//调用dao查询没有预定的餐桌
			return dinnerTableDao.findByStatus(TableStatus.Free);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	
	}

	public DinnerTable findById(int id) {
		try{
			return dinnerTableDao.findById(id);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		
		 
	}

	public List<DinnerTable> findAll() {
		return dinnerTableDao.findAll();
		
	}

	public void save(DinnerTable dinnerTable) {
		try{
			dinnerTableDao.save(dinnerTable);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		try{
			dinnerTableDao.delete(id);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public void update(DinnerTable dinnerTable) {
		try{
			dinnerTableDao.update(dinnerTable);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
