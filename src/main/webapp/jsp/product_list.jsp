<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


	<title>bookStore列表</title>
	<%--导入css --%>
	<link rel="stylesheet" href="../css/main.css" type="text/css" />
	<style>
		body {
			background-color: #7fd400;
		}
	</style>
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;计算机&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent" style="background-color: #fff">
						<tr>
							<td>
								<h1 style="color: #009688;">商品目录</h1>
								<hr />
								<h1 style="color: #009688;">计算机</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${products.allCounts}种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px; height: 30px; background-color: #009688; border-radius: 20px;" >

								</div>
								<table cellspacing="0" class="booklist" >
									<tr>
										<c:forEach items="${products.object}" var="product">
												<td>
													<div class="divbookpic">
														<p>
															<a href="${pageContext.request.contextPath}/product/findById?bid=${product.id}">
																<img src="${pageContext.request.contextPath}/${product.imgurl}" width="115"
																							height="129" border="0" /> </a>
														</p>
													</div>
													<div class="divlisttitle">
														<a style="color: #009688;" href="${pageContext.request.contextPath}/product/findById?bid=${product.id}">书名:${product.name}<br />售价:${product.price} </a>
													</div></td>
												<td>
										</c:forEach>
									</tr>
								</table>

								<div class="pagination">
									<ul>
										<%--上一页--%>
										<c:if test="${products.currentPage == 1}">
											<li class="disablepage">上一页 &lt;&lt;</li>
										</c:if>
										<c:if test="${products.currentPage != 1}">
											<li>
												<a href="${pageContext.request.contextPath}/product/showProductByPage?category=${category}&currentPage=${products.currentPage - 1}">上一页 &lt;&lt;</a></li>
										</c:if>
										<%--遍历页码--%>
										<c:forEach begin="1"  end="${products.allPages}" var="i" >
											<li >
												<a ${i == products.currentPage ? 'class="currentpage"':''} href="${pageContext.request.contextPath}/product/showProductByPage?category=${category}&currentPage=${i}" >${i}</a>
											</li>
										</c:forEach>
										<%--下一页--%>
										<c:if test="${products.currentPage == products.allPages}">
											<li class="disablepage">下一页 &lt;&lt;</li>
										</c:if>
										<c:if test="${products.currentPage != products.allPages}">
											<li><a href="${pageContext.request.contextPath}/product/showProductByPage?category=${category}&currentPage=${products.currentPage + 1}">下一页&gt;&gt;</a>
										</c:if>
										</li>
									</ul>
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>

	<jsp:include page="foot.jsp" />


</body>
</html>
