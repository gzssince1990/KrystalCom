<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Order</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<div class="container">
    <h1>${productName}</h1>
    <form:form action="saveOrder" method="post" modelAttribute="order" class="form-horizontal">
        <form:hidden path="order_id" />
        <form:hidden path="product_id" />
        <div class="form-group">
            <label for="customer_id" class="col-md-2 control-label">Customer: </label>
            <div class="col-md-8">
                <form:select path="customer_id" items="${customerList}" class="form-control" />
            </div>
        </div>

        <div class="form-group">
            <label for="order_status" class="col-md-2 control-label">Order Status: </label>
            <div class="col-md-8">
                <form:select path="order_status" items="${orderStatus}" class="form-control" />
            </div>
        </div>

        <div class="form-group">
            <label for="payment_status" class="col-md-2 control-label">Payment Status: </label>
            <div class="col-md-8">
                <form:select path="payment_status" items="${paymentStatus}" class="form-control" />
            </div>
        </div>

        <div class="form-group">
            <label for="ship_status" class="col-md-2 control-label">Ship Status: </label>
            <div class="col-md-8">
                <form:select path="ship_status" items="${shipStatus}" class="form-control" />
            </div>
        </div>

        <div class="form-group">
            <label for="actual_price" class="col-md-2 control-label">Actual Price: </label>
            <div class="col-md-8">
                <form:input path="actual_price" class="form-control" placeholder="500.0" />
                <div class="has-error">
                    <form:errors path="actual_price" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
                <input type="submit" value="Order" class="btn btn-default">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
