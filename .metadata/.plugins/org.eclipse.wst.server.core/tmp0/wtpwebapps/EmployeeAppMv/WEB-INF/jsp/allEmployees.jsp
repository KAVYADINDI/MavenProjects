<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Employee Details</title>
</head>
<body bgcolor="cyan">
	<h1>All Employee Details</h1>
	<h3>
		<table border="1" cellspacing="5"cellpadding"5">
			<thead>
				<tr>
					<th>Employee Id</th>
					<th>Employee Name</th>
					<th>Employee Department</th>
					<th>Employee Salary</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="employee" items="${allEmployees}">
			<tr>
			<th>${employee.employeeId}</th>
			<th>${employee.employeeName}</th>
			<th>${employee.employeeDepartment}</th>
			<th>${employee.employeeSalary}</th>
			<th><a href="editEmployeePage/${employee.employeeId}">edit</a></th>
			<th><a href="deleteEmployee/${employee.employeeId}">delete</a></th>
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</h3>
</body>
</html>