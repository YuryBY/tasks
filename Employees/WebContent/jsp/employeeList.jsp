<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>



<label class="errors"><html:errors /></label>

<logic:notEmpty name="employeeForm" property="employeeList">
	<table border="1" id="employeesTable">
		<tr>
			<th colspan="3">Employee</th>
			<th colspan="6">Offices</th>
		</tr>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Address</th>
			<th>Company name</th>
			<th>City</th>
			<th>Country</th>
			<th>Address</th>
			<th>Count of employees</th>
			<th>Position</th>
		</tr>

		<logic:iterate id="employee" name="employeeForm"
			property="employeeList" indexId="employeeId">


			<logic:notEmpty name="employee" property="positions">

				<tr>
					<td><bean:write name="employee" property="firstName" /></td>
					<td><bean:write name="employee" property="lastName" /></td>
					<td><bean:write name="employee" property="address.street" /></td>
					<logic:iterate id="job" name="employee" property="positions"
						indexId="officeId">
						<logic:greaterThan name="officeId" value="0">
							<tr bordercolor="white" bgcolor="">
								<td colspan="3"></td>
						</logic:greaterThan>

						<td><bean:write name="job" property="office.company.name" /></td>
						<td><bean:write name="job"
								property="office.address.city.name" /></td>
						<td><bean:write name="job"
								property="office.address.city.country.name" /></td>
						<td><bean:write name="job" property="office.address.street" /></td>
						<td><bean:write name="job" property="office.employeeNumber" /></td>
						<td><bean:write name="job" property="position.name" /></td>

					</logic:iterate>

				</tr>

			</logic:notEmpty>

		</logic:iterate>

	</table>
</logic:notEmpty>