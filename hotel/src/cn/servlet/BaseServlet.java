package cn.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.instance.BeanFactor;
import cn.service.IDinnerTableService;
import cn.service.IFoodService;
import cn.service.IFoodTypeService;
import cn.service.IOrdersService;
import cn.utils.WebUtils;
/**
 * 通用的servlet
 * @author Taeyeon
 *
 */

public abstract class BaseServlet extends HttpServlet{

	//service 对象
	protected IDinnerTableService dinnerTableService=BeanFactor.getInstance("dinnerTableService", IDinnerTableService.class);
	protected IFoodTypeService foodTypeService = BeanFactor.getInstance("foodTypeService", IFoodTypeService.class);
	protected IFoodService foodService=BeanFactor.getInstance("foodService", IFoodService.class);
	protected IOrdersService ordersService=BeanFactor.getInstance("ordersService", IOrdersService.class);
	
	protected Object uri;  //保存跳转资源   （转发/重定向）
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		/*String urie = request.getScheme() + "://" 
		          + request.getServerName() + ":" + request.getServerPort() 
		          + request.getRequestURI() +"?"
		          + (request.getQueryString());
		System.out.println(urie+"////////////////////////////");
*/
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try{
			
			//获取请求参数
			String methodName =request.getParameter("method");
			//获取当前运行类的字节码
			Class clazz=this.getClass();
			//获取当前执行方法的method的执行类型
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			
			uri = method.invoke(this, request,response);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			uri="/error/error.jsp";
		}
		WebUtils.goTo(request, response, uri);
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
