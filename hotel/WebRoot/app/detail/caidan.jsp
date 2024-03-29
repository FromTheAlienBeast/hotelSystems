﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/app/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/app/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/style/css/index_1.css" />
<link href="${pageContext.request.contextPath}/app/style/css/index.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	// 参数： 当前页
	function gotoPage(page){
				
		// 先获取当前表单对象
		var frm = document.forms[0];
		frm.action = "${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail" + "&currentPage="+page ;
		/* alert(frm.action); */
		frm.submit();
	
	}

</script>

</head>
<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 显示菜品的div -->
			<div id="top">
				<ul>
					<!-- 循环列出餐品 -->
					<c:choose>
						<c:when test="${not empty requestScope.pb }">
							<c:forEach var="food" items="${requestScope.pb.pageData }">
								<li>
									<dl>
										<dt>
											<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodXiangxi&foodId=${food.id}">
		<!-- 只有一个图片 -->										<%-- <img width="214px" height="145px" src="${pageContext.request.contextPath}/${food.img }"  value="${food.img }"/> --%>
													<img width="214px" height="145px" src="${pageContext.request.contextPath}/app/style/images/baizhuoxia.jpg" />
											</a>
										</dt>
										<dd class="f1">
											<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodXiangxi&foodId=${food.id}">${food.foodName }</a>
										</dd>
										<dd class="f2">
											<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodXiangxi&foodId=${food.id}">&yen;${food.price }</a>
										</dd>
									</dl>
								</li>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</div>
			
			<!-- 底部分页导航条div -->
			<div id="foot">
				
					
					
						<span
							style="float:left; line-height:53PX; margin-left:-50px; font-weight:bold; ">
							<span style="font-weight:bold">&lt;&lt;</span>
						</span>
					
				
				<div id="btn">
					<ul>
						<!-- 参看 百度, 谷歌是 左 5 右 4 -->
						
							<!-- <li><a href="#">1</a></li>
						
							<li><a href="#">2</a></li> -->
						当前${pb.currentPage }/${pb.totalPage }页 &nbsp;
						<%-- <a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail&currentPage=1">首页</a>
						<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail&currentPage=${pb.currentPage+1}">下一页</a>
						<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail&currentPage=${pb.currentPage-1}">上一页</a>
						<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail&currentPage=${pb.totalPage}">尾页</a>
						 --%>
						<a href="javascript:gotoPage(1)">首页</a>
						 <a href="javascript:gotoPage(${pb.currentPage-1 })">上一页</a>
						 <a href="javascript:gotoPage(${pb.currentPage+1 })">下一页</a>
						 <a href="javascript:gotoPage(${pb.totalPage })">尾页</a>
						
					</ul>
				</div>
				
					
						<span style="float:right; line-height:53px; margin-right:10px;  ">
							<a
							href="#"
							style=" text-decoration:none;color:#000000; font-weight:bold">&gt;&gt;</a>
						</span>
					
					
				
			</div>
			
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/servlet/CarServlet?method=OrderSelect">
							<img src="${pageContext.request.contextPath}/app/style/images/call2.gif" /><!-- 查看清单列表 -->
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					<c:forEach var="foodType" items="${requestScope.foodType }">
						<li>
							<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail&foodTypeId=${foodType.id}">${foodType.typeName }</a>
							<input type="hidden" name="foodTypeId" value="${foodType.id}">
						</li>
					
					</c:forEach>
					
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value"  value=""/> 
								<!-- <input type="hidden" value="selectFood" name="method"> -->
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail">
									<img src="${pageContext.request.contextPath}/app/style/images/look.gif" /> <!-- 查看菜单 -->
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>
