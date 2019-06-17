package cn.service;

import java.util.List;

import cn.etity.DinnerTable;
import cn.etity.TableStatus;

public interface IDinnerTableService {

	List<DinnerTable> findByNoUse();          //查询所有未预定的餐桌
	
	DinnerTable findById(int id);            // 主键查询
	List<DinnerTable> findAll();
	
	void save(DinnerTable dinnerTable);   //添加
	
	void delete(int id);
	
	void update(DinnerTable dinnerTable);
}
