<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.shu {
		font-size: 20px;
	}
</style>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr style="background-color: #d8efed;">
				<td>
					<a href="index.jsp"><h1 style="color: #009688; font-size: 25px;">悦听</h1> </a></td>
				<td style="text-align:right; font-size: 32px;">
					<img width="20" height="20" src="../images/shop-car.png"
												  width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a
						href="${pageContext.request.contextPath}/jsp/cart.jsp" style="color:#009688;" >购物车</a> <span class="shu">|</span> <a style="color:#009688;" href="#">帮助中心</a> <span class="shu">|</span><a style="color:#009688;" href="${pageContext.request.contextPath}/user/ifLogin">我的帐户<c:if test="${not empty user}">
					[${user.username}]
				</c:if></a>
					<span class="shu">|</span> <a href="${pageContext.request.contextPath}/jsp/register.jsp" style="color:#009688;">新用户注册</a></td>
		</tr>
	</table>
</div>