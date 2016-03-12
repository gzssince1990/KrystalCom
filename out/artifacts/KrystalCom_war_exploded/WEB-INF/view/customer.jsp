<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Customer List</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<h2>Welcome to Zhisong's customer list</h2>
<div align="center">
    <div class="container">
        <h1>Customer List</h1>
        <p>
            <a href="/newCustomer" class="btn btn-default">Add Customer</a>
        </p>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <th>No</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Telephone</th>
                <th>Email</th>
                <th>Address</th>
                <th>Action</th>

                <c:forEach var="customer" items="${customerList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${customer.firstname}</td>
                        <td>${customer.lastname}</td>
                        <td>${customer.telephone}</td>
                        <td>${customer.email}</td>
                        <td>${customer.address}</td>
                        <td>
                            <a href="/editCustomer?customer_id=${customer.customer_id}">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/deleteCustomer?customer_id=${customer.customer_id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <p><a href="/newCustomer" class="btn btn-default">Add Customer</a></p>
    </div>
</div>
</body>
</html>
