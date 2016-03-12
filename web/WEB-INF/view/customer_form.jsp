<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>New/Edit Customer</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<div class="form-container">
    <h1>New/Edit Customer</h1>
    <form:form action="saveCustomer" method="post" modelAttribute="customer" class="form-horizontal" role="form">
        <form:hidden path="customer_id" />
        <div class="row">
            <div class="form-group col-md-12">
                <label for="firstname" class="col-md-3 control-label" >First Name</label>
                <div class="col-md-7">
                    <form:input path="firstname" class="form-control input-sm" placeholder="first name"/>
                    <div class="has-error">
                        <form:errors path="firstname" class="text-danger" />
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label for="lastname" class="col-md-3 control-label" >Last Name</label>
                <div class="col-md-7">
                    <form:input path="lastname" class="form-control input-sm" placeholder="last name"/>
                    <div class="has-error">
                        <form:errors path="lastname" class="text-danger" />
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label for="telephone" class="col-md-3 control-label" >Telephone</label>
                <div class="col-md-7">
                    <form:input path="telephone" class="form-control imput-sm" placeholder="2011111001"/>
                    <div class="has-error">
                        <form:errors path="telephone" class="text-danger" />
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label for="email" class="col-md-3 control-label" >Email</label>
                <div class="col-md-7">
                    <form:input path="email" class="form-control input-sm" placeholder="krystal@zhisong.love"/>
                    <div class="has-error">
                        <form:errors path="email" class="text-danger" />
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label for="address" class="col-md-3 control-label" >Address</label>
                <div class="col-md-7">
                    <form:input path="address" class="form-control input-sm" placeholder="1 normal ave, normal NM 07001"/>
                    <div class="has-error">
                        <form:errors path="address" class="text-danger" />
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-offset-10 col-md-2">
                <input type="submit" class="btn btn-primary btn-sm" value="Save" />
            </div>
        </div>
    </form:form>

</div>
</body>
</html>
