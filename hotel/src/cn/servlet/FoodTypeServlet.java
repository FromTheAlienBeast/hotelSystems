package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.etity.FoodType;
import cn.instance.BeanFactor;
import cn.service.IFoodTypeService;

public class FoodTypeServlet extends  HttpServlet{

	private IFoodTypeService foodTypeService = BeanFactor.getInstance("foodTypeService", IFoodTypeService.class);
	private String uri;
	//1.添加菜系
	//2.菜系列表
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//获取操作的类型
		String method=request.getParameter("method");
		System.out.println(method);
		//判断操作类型
		if("addFoodType".equals(method)){
			addFoodType(request,response);
		}else if ("listFoodType".equals(method)){
			listFoodType(request,response);
		}else if("viewUpdate".equals(method)){
			viewUpdate(request,response);
		}else if("delete".equals(method)){
			delete(request,response);
		}else if("update".equals(method)){
			update(request,response);
		}
	}

	
	//添加菜系
	public void addFoodType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			//获取请求的封装数据
			String foodTypeName=request.getParameter("foodTypeName");
			System.out.println(foodTypeName);
			FoodType ft=new FoodType();
			ft.setTypeName(foodTypeName);
			

			//调用setvice处理业务逻辑
			foodTypeService.save(ft);
			//跳转
			 uri="/servlet/FoodTypeServlet?method=listFoodType";
		}catch (Exception e){
			e.printStackTrace();
			 uri="/error/error.jsp";
		}
		
		//转发
		request.getRequestDispatcher(uri).forward(request,response);
		
	}
	//菜系列表
	public void listFoodType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		try {
			// 调用Service查询所有的类别
			List<FoodType> list = foodTypeService.getAll();
			/*if(list!=null)
			for(FoodType l:list){
				System.out.println(l.getTypeName());
			}*/
			//保存
			request.setAttribute("listFood", list);
			//路径
			 uri="/sys/type/foodType_List.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			 uri="/error/error.jsp";
		}
		
		// 跳转
		request.getRequestDispatcher(uri).forward(request,response);
		
	}
	//更新
	public void viewUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			//获取请求的id
			String id=request.getParameter("id");
			
			String typeName=request.getParameter("typeName");
			
			FoodType foodType=new FoodType();
			
			foodType.setId(Integer.parseInt(id));
			foodType.setTypeName(typeName);
			//根据id查询对象
			foodTypeService.update(foodType);
			
			 uri="/servlet/FoodTypeServlet?method=listFoodType";
			
		}catch (Exception e){
			e.printStackTrace();
			 uri="/error/error.jsp";
		}
		// 跳转
			request.getRequestDispatcher(uri).forward(request,response);
	}
	//删除
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			//获取请求的id
			String id=request.getParameter("id");
			//调用service
			foodTypeService.delete(Integer.parseInt(id));
			//跳转
			 uri="/servlet/FoodTypeServlet?method=listFoodType";
			
		}catch (Exception e){
			e.printStackTrace();
			 uri="/error/error.jsp";
		}
		// 跳转
		request.getRequestDispatcher(uri).forward(request,response);
		
	}

	
	//更新
		public void  update(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			try{
					String id = request.getParameter("id");

					request.setAttribute("foodTypeId", id);
					uri="/sys/type/foodType_update.jsp";
			}catch (Exception e){
				e.printStackTrace();
				 uri="/error/error.jsp";
			}
			// 跳转
			request.getRequestDispatcher(uri).forward(request,response);
			
				
			
		}

		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request,response);
	}

}
