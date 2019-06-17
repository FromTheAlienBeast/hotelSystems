package cn.daoImp;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.dao.IDinnerTableDao;
import cn.etity.DinnerTable;
import cn.etity.TableStatus;
import cn.utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao{

	public List<DinnerTable> findByStatus(TableStatus ts) {
		String sql="select * from DinnerTable where tableStatus=?";
		/*int status=-1;
		if(ts==TableStatus.Free){
			status=0;
		}else{
			status=1;
		}*/   
		//ts 是枚举类型  枚举类型中的数默认是有下标的
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class),ts.ordinal());
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	
	}

	public DinnerTable findById(int id) {
		String sql="select * from DinnerTable where id=?";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class),id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public List<DinnerTable> findAll() {
		String sql="select * from DinnerTable";
		try{
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	
	}

	public void save(DinnerTable dinnerTable) {
		String sql="insert into DinnerTable(tableName) values(?);";
				
		try { 
			JdbcUtils.getQueryRunner().update(sql,dinnerTable.getTableName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

	public void delete(int id) {
		String sql="delete from DinnerTable where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(DinnerTable dinnerTable) {
		
		String sql="update DinnerTable set tableStatus=?,orderDate=? where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,dinnerTable.getTableStatus(),dinnerTable.getOrderDate(),dinnerTable.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
