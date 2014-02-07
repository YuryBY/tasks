<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/initial.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/view.css" />
<title>Page</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/controller?command=to main"
		id="parserRef">To The Main Page</a>
	<!-- <div id="parserName">Parser Name: -->
	<%-- <c:out value="${parserName}" /></div> --%>
	<c:forEach var="category" items="${categories}">
		<div class="category">
			CATEGORY
			<c:out value="${category.name}" />
		</div>
		<c:forEach var="subcategory" items="${category.iterator()}">
			<div class="subcategory">
				SUBCATEGORY
				<c:out value="${subcategory.name}" />
			</div>
			<c:forEach var="product" items="${subcategory.iterator()}">
				<div class="product">
					<div class="productField">
						PRODUCT
						<c:out value="${product.name}" />
					</div>
					<div class="productField">
						<c:out value="${product.producer}" />
					</div>
					<div class="productField">
						<c:out value="${product.model}" />
					</div>
					<div class="productField">
						<c:out value="${product.dateIssue}" />
					</div>
					<div class="productField">
						<c:out value="${product.color}" />
					</div>
					<c:choose>
						<c:when test="${product.price!=0}">
							<div class="productField">
								<c:out value="${product.price}" />
							</div>
						</c:when>
						<c:otherwise>
							<div class="productField">
								<c:out value="${product.missMessage}" />
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</c:forEach>
	</c:forEach>
</body>
</html>
