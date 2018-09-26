<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Search For Employee</h1>
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
			<tr>
			<th>${employee.employeeId}</th>
			<th>${employee.employeeName}</th>
			<th>${employee.employeeDepartment}</th>
			<th>${employee.employeeSalary}</th>
			<th><a href="editEmployeePage/${employee.employeeId}">edit</a></th>
			<th><a href="deleteEmployee/${employee.employeeId}">delete</a></th>
			</tr>
			</tbody>
		</table>
	</h3>
</body>
</html>