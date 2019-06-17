package cn.utils;

import java.util.List;

import cn.etity.Food;

/**
 * 分装分页数据
 * @author Taeyeon
 *
 */
public class PageBean<Food> {

	// 当前页
	private int currentPage=1;
	// 每页显示的行数
	private int pageCount = 6;
	// 总记录数
	private int totalCount;
	// 总页数
	private int totalPage;               //等于总记录除以每页显示行数
	// 每页的数据
	private List<Food> pageData;
	
	// 封装所有的查询条件
	private Condition condition;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		if(totalCount%pageCount==0){
			return totalCount/pageCount;
		}else{
			return totalCount/pageCount+1;
		}
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Food> getPageData() {
		return pageData;
	}

	public void setPageData(List<Food> pageData) {
		this.pageData = pageData;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
