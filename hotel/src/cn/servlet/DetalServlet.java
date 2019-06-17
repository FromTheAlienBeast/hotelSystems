package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.etity.DinnerTable;
import cn.etity.Food;
import cn.etity.FoodType;
import cn.instance.BeanFactor;
import cn.service.IDinnerTableService;
import cn.service.IFoodService;
import cn.service.IFoodTypeService;
import cn.service.IOrdersService;
import cn.utils.Condition;
import cn.utils.PageBean;

public class DetalServlet extends HttpServlet {

	protected IDinnerTableService dinnerTableService=BeanFactor.getInstance("dinnerTableService", IDinnerTableService.class);
	protected IFoodTypeService foodTypeService = BeanFactor.getInstance("foodTypeService", IFoodTypeService.class);
	protected IFoodService foodService=BeanFactor.getInstance("foodService", IFoodService.class);
	protected IOrdersService ordersService=BeanFactor.getInstance("ordersService", IOrdersService.class);
	
	protected String uri;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//获取操作的类型
		String method=request.getParameter("method");
		System.out.println(method);
		//判断操作类型
		if("listFood".equals(method)){
			listFood(request,response);
		}else if ("foodDetail".equals(method)){
			foodDetail(request,response);
		}else if("foodXiangxi".equals(method)){
			foodXiangxi(request,response);
		}else if("FoodType".equals(method)){
			FoodType(request,response);
		}else if("updateFoodType".equals(method)){
			updateFoodType(request,response);
		}else if("save".equals(method)){
			save(request,response);
		}else if("delete".equals(method)){
			delete(request,response);
		}else if("update".equals(method)){
			update(request,response);
		}
	}
	
	//菜单列表
	public void listFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			List<Food> list=foodService.getAllFood();
			
			request.setAttribute("detal", list);
			//跳转
			uri="/sys/food/foodList.jsp";
		}catch(Exception e){
			e.printStackTrace();
			 uri="/error/error.jsp";
		}
		
		request.getRequestDispatcher(uri).forward(request,response);
		
		
	}
	
	
	/**
	 * 1. 进入主页，显示菜品以及菜系
	 */
	public void foodDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//1.1 获取餐桌ID,根据ID查询，再把查询到的结果保存到session （生成订单用）
		// 只需要执行一次即可: 先从session获取看有没有餐桌对象； 如果没有，就执行根据主键查询；
		// 如果sesison中已经有餐桌对象，就不执行主键查询
		Object obj = session.getAttribute("dinnerTable");
		// 判断
		if (obj == null){
			// 只在第一次执行的时候，查询餐桌对象
			String Id = request.getParameter("Id");
			DinnerTable dt = dinnerTableService.findById(Integer.parseInt(Id));
			// 保存到session
			session.setAttribute("dinnerTable", dt);
		
		}
		
		//1.2 查询所有的“菜品信息”, 保存
		PageBean<Food> pb = new PageBean<Food>();
		// 分页参数： 获取当前页参数
		String currentPage = request.getParameter("currentPage");

		// 判断
		if (currentPage == null || "".equals(currentPage.trim())) {
			// 第一次访问，设置当前页为1
			pb.setCurrentPage(1);
		} else {
			// 【设置当前页参数】
			pb.setCurrentPage(Integer.parseInt(currentPage));
		}
		
		// 条件对象
		Condition condition = new Condition();
		// 分页参数： 菜系id
		String foodTypeId = request.getParameter("foodTypeId");
		
		if (foodTypeId != null) {  // 如果类别为null,不作为条件，那就查询全部
			// 设置条件
			condition.setFoodTypeId(Integer.parseInt(foodTypeId));
		}
		// 分页参数： 菜名称
		String foodName = request.getParameter("foodName");
		// 设置菜品参数
		condition.setFoodName(foodName);
		
		// 【设置条件对象到pb中】
		pb.setCondition(condition);

		// ---->分页查询
		foodService.getAll(pb);
		// 保存查询后的pb对象
		request.setAttribute("pb", pb);
		
		//1.3 查询所有的“菜系信息”， 保存
		List<FoodType> listFoodType = foodTypeService.getAll();
		request.setAttribute("foodType", listFoodType);
		
		//1.4 跳转(转发)
		uri="/app/detail/caidan.jsp";
	
		request.getRequestDispatcher(uri).forward(request,response);
	}
	
	//进入菜详细
	public void foodXiangxi(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		//根据菜品的id查询
		String foodId = request.getParameter("foodId");
	
		//为什么换成 FindById查询  查询的结果始终不变？？？？
		Food food = foodService.getFoodById(Integer.parseInt(foodId));         
	
		request.setAttribute("food", food);
		
		List<FoodType> listFoodType = foodTypeService.getAll();
		request.setAttribute("foodType", listFoodType);
		uri="/app/detail/caixiangqing.jsp";
		request.getRequestDispatcher(uri).forward(request,response);
		
	}
	
	//菜品 添加
	public void FoodType(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		List<FoodType> listFoodType = foodTypeService.getAll();
		
		request.setAttribute("foodType", listFoodType);
		
		uri="/sys/food/saveFood.jsp";
		request.getRequestDispatcher(uri).forward(request,response);
	}
	
	//更新   菜品
	public void updateFoodType(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		List<FoodType> listFoodType = foodTypeService.getAll();
		
		String foodId = request.getParameter("foodId");
		request.setAttribute("foodType", listFoodType);
		request.setAttribute("foodId", foodId);
		uri="/sys/food/updateFood.jsp";
		request.getRequestDispatcher(uri).forward(request,response);
	}
	
	//添加菜品
		public void save(HttpServletRequest request,HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("------*************-------");
			String foodType_id = request.getParameter("cid");
			System.out.println(foodType_id);
			String foodName = request.getParameter("foodName");
			System.out.println(foodName);
			String price=request.getParameter("price");
			System.out.println(price);
			String mprice=request.getParameter("mprice");
			System.out.println(mprice);
			String remark=request.getParameter("remark");
			System.out.println(remark);
			String img=request.getParameter("img");
			System.out.println(img);
			
			Food food=new Food();
			food.setFoodName(foodName);
			food.setPrice(Double.parseDouble(price));
			food.setMprice(Double.parseDouble(mprice));
			food.setRemark(remark);
			food.setImg(img);
			food.setFoodType_id(Integer.parseInt(foodType_id));
			
			foodService.save(food);
			 uri="/servlet/DetalServlet?method=listFood";
			 request.getRequestDispatcher(uri).forward(request,response);
		}//save 方法的save中添加不进去数据
		
		//删除
		public void delete(HttpServletRequest request,HttpServletResponse response)
				throws ServletException, IOException {
			String id = request.getParameter("id");
			
			foodService.delete(Integer.parseInt(id));
			uri="/servlet/DetalServlet?method=listFood";
			 request.getRequestDispatcher(uri).forward(request,response);
		}
		
		
		//更新
		public void update(HttpServletRequest request,HttpServletResponse response)
				throws ServletException, IOException {
			
				//获取请求的id
				String foodType_id=request.getParameter("cid");
				System.out.println(foodType_id);
				String foodName=request.getParameter("foodName");
				System.out.println(foodName);
				String price=request.getParameter("price");
				System.out.println(price);
				String mprice=request.getParameter("mprice");
				String remark=request.getParameter("remark");
				String img=request.getParameter("img");
				String foodId=request.getParameter("foodId");
				System.out.println(foodId);
				
				Food food=new Food();
				food.setFoodType_id(Integer.parseInt(foodType_id));
				food.setFoodName(foodName);
				food.setPrice(Double.parseDouble(price));
				food.setMprice(Double.parseDouble(mprice));
				food.setRemark(remark);
				food.setImg(img);
				food.setId(Integer.parseInt(foodId));
				
				foodService.update(food);
				
				uri="/servlet/DetalServlet?method=listFood";
				 request.getRequestDispatcher(uri).forward(request,response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			this.doGet(request, response);
		}
		

}
