<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/employeeList.css">
<script src="${pageContext.request.contextPath}/js/employeeList.js"></script>
</head>
<body onload="start(${employeeForm.pageItem.itemCount}, ${employeeForm.pageItem.itemOnPage}, ${employeeForm.pageItem.currentPage})">
	<ctg:item-per-table valueList="${employeeForm.pageItem.listItemPage}" currentNumber="${employeeForm.pageItem.itemOnPage}"/>
	<ctg:current-page pageNumber="${employeeForm.pageItem.currentPage}" 
			noteCount="${employeeForm.pageItem.itemCount}"
			noteCountOnPage="${employeeForm.pageItem.itemOnPage}"/>
	<ctg:navigation notesCount="${employeeForm.pageItem.itemCount}" notesOnPage="${employeeForm.pageItem.itemOnPage}"/>
	<table cellpadding="0" cellspacing="0">
		<tbody>
			<c:forEach var="employee" items="${employeeForm.employeeList}" varStatus="employeeNumber">
				<tr>
					<td rowspan="${employee.jobSet.size()}">
						<c:out value="${employee.id}"/>
					</td>
					<td rowspan="${employee.jobSet.size()}">
						<c:out value="${employee.firstName}"/>, 
						<c:out value="${employee.lastName}"/>
					</td>
					<td class="sizeRestriction" rowspan="${employee.jobSet.size()}">
						<c:set var="address" value="${employee.address}"/>
						<c:out value="${address.city.country.name}"/>,
						<c:out value="${address.city.name}"/>,
						st. <c:out value="${address.street}"/>, 
						hs. <c:out value="${address.house}"/>, 
						rm. <c:out value="${address.room}"/>,
						hsg <c:out value="${address.housing}"/>
					</td>
						<c:set var="office" value="${employee.jobSet.iterator().next().office}"/>
						<td>
							<c:out value="${office.company.name}"/>
						</td>
						<td>
							<c:out value="${office.address.city.name}"/>
						</td>
						<td>
							<c:out value="${office.address.city.country.name}"/>
						</td>
						<td class="sizeRestriction">
							st. <c:out value="${address.street}"/>, 
							hs. <c:out value="${address.house}"/>, 
							rm. <c:out value="${address.room}"/>,
							hsg <c:out value="${address.housing}"/>
						</td>
						<td>
							<c:out value="${office.employeeCount}"/>
						</td>
						<td>
							<c:out value="${employee.jobSet.iterator().next().position.name}"/>
						</td>
				</tr>
				<c:forEach var="job" items="${employee.jobSet}" begin="1">
					<c:set var="office" value="${job.office}"/>
					<tr>
						<td>
							<c:out value="${office.company.name}"/>
						</td>
						<td>
							<c:out value="${office.address.city.name}"/>
						</td>
						<td>
							<c:out value="${office.address.city.country.name}"/>
						</td>
						<td>
							st. <c:out value="${office.address.street}"/>, 
							hs. <c:out value="${office.address.house}"/>, 
							rm. <c:out value="${office.address.room}"/>,
							hsg <c:out value="${office.address.housing}"/>
						</td>
						<td>
							<c:out value="${job.office.employeeCount}"/>
						</td>
						<td>
							<c:out value="${job.position.name}"/>
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
		<thead>
			<tr>
				<th rowspan="2">
					ID
				</th>
				<th rowspan="2">
					Name
				</th>
				<th rowspan="2">
					Address
				</th>
				<th colspan="6">
					Office
				</th>
			</tr>
			<tr>
				<th>
					Company Name
				</th>
				<th>
					City
				</th>
				<th>
					Country
				</th>
				<th>
					Address
				</th>
				<th>
					Count of Employee
				</th>
				<th>
					Position
				</th>
			</tr>
		</thead>
	</table>
	<div style="margin-bottom: 10px"></div>
	<ctg:current-page pageNumber="${employeeForm.pageItem.currentPage}" 
			noteCount="${employeeForm.pageItem.itemCount}"
			noteCountOnPage="${employeeForm.pageItem.itemOnPage}"/>
	<ctg:navigation 
			notesCount="${employeeForm.pageItem.itemCount}" 
			notesOnPage="${employeeForm.pageItem.itemOnPage}"/>
	<ctg:item-per-table 
			valueList="${employeeForm.pageItem.listItemPage}" 
			currentNumber="${employeeForm.pageItem.itemOnPage}"/>
</body>
</html>