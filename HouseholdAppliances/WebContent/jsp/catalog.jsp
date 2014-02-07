<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.content">
	<html>
<head>
<title><fmt:message key="index.title" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css" media="all">
</head>
<body>

	<c:set var="outCommand" scope="page">
		logout
	</c:set>

	<!-- SideBar -->
	<%@ include file="jspf/side_bar.jspf"%>

	<!-- RightSide  -->
	<%@ include file="jspf/right_side.jspf"%>

	<!-- Main Part  -->
	<div id="main">

		<!-- Main Part>Top -->
		<%@ include file="jspf/top.jspf"%>
		<div class="post">
			<h2 class="title">
				<%-- <fmt:message key="index.main.music_list" /> --%>
			</h2>
			<div class="meta"></div>

			<div class="story">
				<div class="toPreviousPage">
					<button OnClick="JavaScript:window.history.back(1);return false;">To
						Previous Page</button>
				</div>
				${catalog}

		<%-- 		<c:if test="${not empty subcategoryName}">
					<!-- <br /> -->
					<!-- <a href="controller?command=add">Add Goods</a> -->
					<!-- 	<form class="addForm" name="createForm"
						action="controller?command=add">
						<input type="submit" value="Add Goods" />
					</form> -->
				</c:if> --%>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<%@ include file="jspf/footer.jspf"%>

	<!-- Appearing registration page -->
	<%@ include file="jspf/registration.jspf"%>

</body>
	</html>
</fmt:bundle>