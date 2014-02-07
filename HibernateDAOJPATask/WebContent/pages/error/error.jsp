<%@ page isErrorPage="true" language="java"	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>error page</title>
	</head>
	<body>
		<div id="main">
			<div id="message">message</div>
			<table border="1">
				<thead>
					<tr>
						<th>
							description
						</th>
						<th>
							type
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							failed from
						</td>
						<td>
							 ${pageContext.errorData.requestURI}
						</td>
					</tr>
					
					<tr>
						<td>
							servlet name
						</td>
						<td>
							${pageContext.errorData.servletName}
						</td>
					</tr>
					
					<tr>
						<td>
							status code
						</td>
						<td>
							${pageContext.errorData.statusCode}
						</td>
					</tr>
					
					<tr>
						<td>
							exception
						</td>
						<td>
							${pageContext.errorData.throwable}
						</td>
					</tr>
				</tbody>
			</table>
			<form id="form" method="POST" action="${pageContext.request.contextPath}/Controller">
				<input type="hidden" name="command" value="home" />
				<input type="submit" value="return">
			</form>
		</div>
	</body>
</html>
