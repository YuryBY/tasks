<%@ page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${locale}" />
<fmt:bundle basename="properties.content">
	<html>
<head>
<title><fmt:message key="error.title" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../css/index.css"
	media="all">
</head>
<body>

	<!-- Main Part  -->
	<div id="main">

		<!-- Main Part>Top -->
		<%@ include file="../jspf/top.jspf"%>

		<!-- Main Part>Error Description -->
		<div class="error">
			<c:choose>
				<c:when test="${pageContext.errorData.statusCode==404}">
					<fmt:message key='error.code.notFound' />
					<a href="${pageContext.request.contextPath}/controller"> 
					<fmt:message key="error.code.main" /></a>
  				</c:when>
				<c:when test="${not empty optionError}">
    				${optionError}
  				</c:when>
				<c:otherwise>
					<fmt:message key='error.request' />
    				${pageContext.errorData.requestURI} 
    				<fmt:message key='error.request.failed' />
					<fmt:message key='error.servletName' />
					${pageContext.errorData.servletName}
					<fmt:message key='error.code' />	
					${pageContext.errorData.statusCode}
					<c:if test="${not empty pageContext.errorData.throwable}">
						<fmt:message key='error.exception' /> 
						${pageContext.errorData.throwable}
						<fmt:message key='error.exception.massage' /> 
						${pageContext.exception.message}
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- Footer -->
	<div class="errorFooteer">
	<%@ include file="../jspf/footer.jspf"%>
	</div>

</body>
	</html>
</fmt:bundle>




