﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html >

<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/app/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/app/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/app/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/style/css/index_1.css" />
	<link href="${pageContext.request.contextPath }/app/style/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/style/css/dis_message.css" />
</head>
<body style="text-align: center">
	<div id="all">
		<!--左边菜品详细信息 -->
		<div id="menu1">
			<div class="menu2" style="text-align:center;">
				<img src="${pageContext.request.contextPath }/app/style/images/order_detials_bg.png" />
			</div>
			<div class="menu3">
				<div class="menu3_left">
					<img src="${pageContext.request.contextPath }/app/style/images/baizhuoxia.jpg"
						style="width:270px; height:290px;" />
				</div>
				<div class="menu3_right">
					
						<p>菜名:${requestScope.food.foodName }</p>
						<p>价格:&nbsp;&nbsp;&yen;&nbsp;${requestScope.food.price }</p>
						<p>简介：${requestScope.food.remark }</p>
					
				</div>
			</div>
			<div class="menu4">
				
				<a href="${pageContext.request.contextPath }/servlet/CarServlet?method=OrderDetail&foodId=${food.id}&tableId=${sessionScope.dinnerTable.id}" style="background:url(style/images/img/order_left_corner_bg.png);">放入餐车</a>
				<a href="#" onclick="javascript:history.go(-1);" style="background:url(${pageContext.request.contextPath }/app/style/images/img/order_right_corner_bg.png);">返回</a>
			</div>
		</div>
		
		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/servlet/CarServlet?method=OrderSelect">
							<img src="${pageContext.request.contextPath }/app/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					
						<c:forEach var="foodType" items="${requestScope.foodType }">
						<li>
							<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail">${foodType.typeName }</a>
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
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<!-- <input type="hidden" value="selectFood" name="method"> -->
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/servlet/DetalServlet?method=foodDetail">
									<img src="${pageContext.request.contextPath}/app/style/images/look.gif" />
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
