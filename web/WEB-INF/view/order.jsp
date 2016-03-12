<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order List</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<h2>Welcome to Zhisong's Order list</h2>
<div align="center">
    <div class="container">
        <h1>Order List</h1>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <th>No</th>
                <th>Customer</th>
                <th>Product</th>
                <th>Order Status</th>
                <th>Payment Status</th>
                <th>Ship Status</th>
                <th>Actual Price</th>
                <th>Action</th>

                <c:forEach var="orderStr" items="${orderStrList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${orderStr.customer_name}</td>
                        <td>${orderStr.product_name}</td>
                        <td>${orderStr.order_status}</td>
                        <td>${orderStr.payment_status}</td>
                        <td>${orderStr.ship_status}</td>
                        <td>${orderStr.actual_price}</td>
                        <td>
                            <a href="/editOrder?order_id=${orderStr.order_id}">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/deleteOrder?order_id=${orderStr.order_id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
