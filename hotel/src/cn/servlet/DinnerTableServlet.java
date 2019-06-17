package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.etity.DinnerTable;
import cn.instance.BeanFactor;
import cn.service.IDinnerTableService;
import cn.serviceImp.DinnerTableService;

public class DinnerTableServlet extends /*HttpServlet*/ BaseServlet{

	
	/**
	 * 通过继承BaseServlet 然后在baseServlet中重写 doget  和dopost方法  
	 * 当jsp页面执行时，路径请求的setvlet找到子类servlet，但是子类servlet中没有doget或者 dopost方法
	 * 就会去父类中查看  调用doget或者dopost方法，然后获取servlet中携带的参数 ，再在子类中调用相应的子类的方法
	*/
	
	
	/*private IDinnerTableService dinnerTableService=BeanFactor.getInstance("dinnerTableService", IDinnerTableService.class);
	private Object uri;  //保存跳转资源   （转发/重定向）
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//获取请求方法
		String method=request.getParameter("method");
	
		//默认进入前台的首页
		if(method==""){
			method="listTable";
		}
		if("listTable".equals(method)){
			//前台首页显示所有未预定的餐桌
			listTable(request,response);
		}
		
	}

	
	*//**
	 * 跳转的通用方法
	 *//*
	private void goTo(HttpServletRequest request, HttpServletResponse response, Object uri)
			throws ServletException, IOException {
		if (uri instanceof RequestDispatcher){
			((RequestDispatcher)uri).forward(request, response);
		} else if (uri instanceof String) {
			response.sendRedirect(request.getContextPath() + uri);
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
		
	}*/
	
	public /*void*/ Object listTable(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		try{
			//查询所有未预定的餐桌
			List<DinnerTable> list = dinnerTableService.findByNoUse();
		
			//保存
			request.setAttribute("listDinnerTable", list);
			//跳转
			uri=request.getRequestDispatcher("/app/index.jsp");
			}catch (Exception e){
				e.printStackTrace();
				
				uri="/error/error.jsp";
			}
			return uri;
			/*//转发
			//request.getRequestDispatcher(uri).forward(request,response);
		goTo(request,response,uri);*/
			
	}
	//查询所有餐桌的状态
	public  Object listAllTable(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		try{
			//查询所有的餐桌
			List<DinnerTable> list = dinnerTableService.findAll();
		
			//保存
			request.setAttribute("listDinnerTable", list);
			//跳转
			uri=request.getRequestDispatcher("/sys/table/BoardList.jsp");
			}catch (Exception e){
				e.printStackTrace();
				
				uri="/error/error.jsp";
			}
			return uri;
			/*//转发
			//request.getRequestDispatcher(uri).forward(request,response);
		goTo(request,response,uri);*/
			
	}
	
	//添加
	public  Object save(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String tableName = request.getParameter("tableName");
		DinnerTable dinnerTable=new DinnerTable();
		
		dinnerTable.setTableName(tableName);
		dinnerTable.setTableStatus(0);
		
		dinnerTableService.save(dinnerTable);
		
		
		return listAllTable(request,response);
	}
	
	//删除
		public  Object delete(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			String id = request.getParameter("id");
			
			dinnerTableService.delete(Integer.parseInt(id));
			
			return listAllTable(request,response);
		}
		
		//更新
		public  Object update(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			String id = request.getParameter("id");
			DinnerTable dinnerTable=new DinnerTable();
			
			dinnerTable.setId(Integer.parseInt(id));
			dinnerTable.setTableStatus(1);
			//设置时间
			SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
			dinnerTable.setOrderDate(df.format(new Date()));
			dinnerTableService.update(dinnerTable);
			
			
			return listAllTable(request,response);
		}
		

}
