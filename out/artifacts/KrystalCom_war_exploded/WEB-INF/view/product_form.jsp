<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Product</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<div class="container">
    <h1>New/Edit Product</h1>
    <form:form action="saveProduct" method="post" modelAttribute="product"
    enctype="multipart/form-data" class="form-horizontal">
        <form:hidden path="product_id" />
        <div class="form-group">
            <label for="brand" class="col-md-2 control-label">Brand: </label>
            <div class="col-md-8">
                <form:input path="brand" class="form-control input-sm" placeholder="Nike" />
                <div class="has-error">
                    <form:errors path="brand" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Product Name: </label>
            <div class="col-md-8">
                <form:input path="name" class="form-control input-sm" placeholder="Jammer" />
                <div class="has-error">
                    <form:errors path="name" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="color" class="col-md-2 control-label">Color: </label>
            <div class="col-md-8">
                <form:input path="color" class="form-control input-sm" placeholder="Jammer" />
                <div class="has-error">
                    <form:errors path="color" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="size" class="col-md-2 control-label">Size: </label>
            <div class="col-md-8">
                <form:input path="size" class="form-control input-sm" placeholder="Black" />
                <div class="has-error">
                    <form:errors path="size" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="description" class="col-md-2 control-label">Description: </label>
            <div class="col-md-8">
                <form:input path="description" class="form-control input-sm" placeholder="Descript here" />
                <div class="has-error">
                    <form:errors path="description" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">Upload image: </label>
            <div class="col-md-8">
                <input name="image" type="file" />
            </div>
        </div>

        <div class="form-group">
            <label for="cost_usd" class="col-md-2 control-label">Cost USD: </label>
            <div class="col-md-8">
                <form:input path="cost_usd" class="form-control input-sm" placeholder="10.0" />
                <div class="has-error">
                    <form:errors path="cost_usd" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="cost_chy" class="col-md-2 control-label">Cost CHY: </label>
            <div class="col-md-8">
                <form:input path="cost_chy" class="form-control input-sm" placeholder="67.0" />
                <div class="has-error">
                    <form:errors path="cost_chy" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="price_chy" class="col-md-2 control-label">Price CHY: </label>
            <div class="col-md-8">
                <form:input path="price_chy" class="form-control input-sm" placeholder="200.00" />
                <div class="has-error">
                    <form:errors path="price_chy" class="text-danger" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-10 col-md-2">
                <input type="submit" class="btn btn-default" value="Save">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
