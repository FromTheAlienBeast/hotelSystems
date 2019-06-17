package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.etity.Food;
import cn.etity.FoodType;
import cn.etity.Orders;

public class CarServlet extends BaseServlet {

	//放入餐车
	public Object OrderDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
	
		String foodId = request.getParameter("foodId");
		
		//查询food的相应信息
		Food food = foodService.getFoodById(Integer.parseInt(foodId));
		
		//获取餐桌id   tableId
		String tableId=request.getParameter("tableId");
		
		Orders orders=new Orders();
		
		orders.setTable_id(Integer.parseInt(tableId));     		
		orders.setNumbers(1);
		orders.setOrderStatus(0);
		
		orders.setFoodName(food.getFoodName());
		orders.setPrice(food.getPrice());
		
		ordersService.save(orders);

		return uri="/servlet/CarServlet?method=OrderSelect";
	}
	
	
	//删除
	public Object OrderDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");

		ordersService.delete(Integer.parseInt(orderId));
		return uri="/servlet/CarServlet?method=OrderSelect";
	}
	
	//查看所有的订单
	
		public Object OrderSelect(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			//菜系类别
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("foodType", listFoodType);
			
			List<Orders> list = ordersService.select();
			request.setAttribute("order", list);
			
			//计算总价格
			double total = ordersService.total();
			request.setAttribute("total", total);
			return request.getRequestDispatcher("/app/detail/clientCart.jsp");
			
		}
		
		//下单
		public Object OrdersOrder(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			//查询订单
			List<Orders> list = ordersService.select();
			request.setAttribute("order", list);
			
			List<FoodType> listFoodType = foodTypeService.getAll();
			request.setAttribute("foodType", listFoodType);
			//计算总价格
			double total = ordersService.total();
			request.setAttribute("total", total);
			return request.getRequestDispatcher("/app/detail/clientOrderList.jsp");
		}
		
		//更新
		public Object OrderUpdate(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String id=request.getParameter("id");
			System.out.println("id="+id);
			String numbers = request.getParameter("numbers");
			System.out.println("numbers="+numbers);
			Orders order=new Orders();
			order.setId(Integer.parseInt(id));
			order.setNumbers(Integer.parseInt(numbers));
			
			System.out.println("totalPrice"+order.getTotalPrice());
			ordersService.update(order);
			return  uri="/servlet/CarServlet?method=OrderSelect";
		}
		
		//根据餐桌进行分类 再将对应的餐桌的状态和餐桌的状态修改
		public Object jiezhang(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Orders order=new Orders();
			
			return request.getRequestDispatcher("/app/detail/secced.jsp");
		}
		

}
