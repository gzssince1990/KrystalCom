<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Order</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<div align="center">
    <h1>New/Edit Order</h1>
    <form:form action="saveOrder" method="post" modelAttribute="order">
        <table>
            <form:hidden path="order_id" />
            <tr>
                <td>Customer: </td>
                <td><form:input path="customer_id" /></td>
            </tr>
            <tr>
                <td>Product: </td>
                <td><form:input path="product_id" /></td>
            </tr>
            <tr>
                <td>Ship Id: </td>
                <td><form:input path="ship_id" /></td>
            </tr>
            <tr>
                <td>Order Status: </td>
                <td><form:input path="order_status" /></td>
            </tr>
            <tr>
                <td>Payment Status: </td>
                <td><form:input path="payment_status" /></td>
            </tr>
            <tr>
                <td>Ship Status: </td>
                <td><form:input path="ship_status" /></td>
            </tr>
            <tr>
                <td>Actual Price: </td>
                <td><form:input path="actual_price" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
