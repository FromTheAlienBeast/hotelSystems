package cn.dao;

import java.util.List;

import cn.etity.DinnerTable;
import cn.etity.FoodType;
import cn.etity.TableStatus;

public interface IDinnerTableDao {

	List<DinnerTable> findByStatus(TableStatus ts);          //查询所有未预定的餐桌
	
	DinnerTable findById(int id);            // 主键查询
	
	List<DinnerTable> findAll();       //查询所有
	
	void save(DinnerTable dinnerTable);   //添加
	
	void delete(int id);
	
	void update(DinnerTable dinnerTable);
}
