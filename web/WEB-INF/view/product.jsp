<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<h2>Welcome to Zhisong's product list</h2>
<div align="center">
    <div class="container">
        <h1>Product List</h1>
        <p><a href="/newProduct" class="btn btn-primary btn-md">Add Product</a></div></p>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <th>No</th>
                <th>Brand</th>
                <th>Product Name</th>
                <th>Color</th>
                <th>Size</th>
                <th>Description</th>
                <th>Pic</th>
                <th>Cost (USD)</th>
                <th>cost (CHY)</th>
                <th>price (CHY)</th>
                <th>Action</th>

                <c:forEach var="product" items="${productList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${product.brand}</td>
                        <td>${product.name}</td>
                        <td>${product.color}</td>
                        <td>${product.size}</td>
                        <td>${product.description}</td>
                        <td><img src="data:image/jpeg;base64,${product.imageStr}" alt="failed to load image" width="200" height="200"></td>
                        <td>${product.cost_usd}</td>
                        <td>${product.cost_chy}</td>
                        <td>${product.price_chy}</td>
                        <td>
                            <a href="/editProduct?product_id=${product.product_id}">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/deleteProduct?product_id=${product.product_id}">Delete</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/orderProduct?product_id=${product.product_id}">Order</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <p><a href="/newProduct" class="btn btn-primary btn-md">Add Product</a></p>
    </div>
</div>

</body>
</html>
