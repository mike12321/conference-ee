<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/bodytag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.get(\"locale\")}" />
<fmt:setBundle basename="legend" var="legend"/>
<fmt:setBundle basename="buttons" var="buttons"/>
<html>
<head>
    <title><fmt:message key="newProduct.title" bundle="${legend}"/></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/css/tempusdominus-bootstrap-4.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<style>
    <%@include file='admin_style.css' %>
</style>
<body>
<h1><fmt:message key="newProduct.h1" bundle="${legend}"/></h1>
<div class="widemenu">
    <p><c:out value="${user.name}, ${user.userRole}"/></p>
</div>
<div class = "inner_div">
    <form name="newProductForm" method="post" action="project" >
        <input type="hidden" name="command" value="addNewEvent" />
        <h4><fmt:message key="newProduct.eventTitle" bundle="${legend}"/></h4>
        <input class="input" type="text" name="title" required/><br/>
        <label for="date"><fmt:message key="newProduct.date" bundle="${legend}"/>:</label>
        <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
            <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"
                   name="date" id="date" placeholder="Date" required/>
            <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                <div class="input-group-text"><i class="fa fa-calendar-alt"></i></div>
            </div>
        </div>
        <div class="button_div">
            <button class="bigbutton">
                <fmt:message key="newProduct.add" bundle="${buttons}"/>
            </button>
            <button class="bigbutton" onclick="history.back(); return false;">
                <fmt:message key="newProduct.cancel" bundle="${buttons}"/>
            </button>
        </div>
    </form>
</div>
<t:colontitle/>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://unpkg.com/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://momentjs.com/downloads/moment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/js/tempusdominus-bootstrap-4.min.js"></script>
<script> // TODO: move to appropriate file
    $.fn.datetimepicker.Constructor.Default = $.extend({}, $.fn.datetimepicker.Constructor.Default, {
        icons: {
            time: 'far fa-clock',
            date: 'far fa-calendar',
            up: 'fas fa-arrow-up',
            down: 'fas fa-arrow-down',
            previous: 'fas fa-chevron-left',
            next: 'fas fa-chevron-right',
            today: 'far fa-calendar-check-o',
            clear: 'far fa-trash',
            close: 'far fa-times'
        } });

    $('#datetimepicker1').datetimepicker({
        format: 'DD/MM/YYYY hh:mm A'
    });
</script>
</body>
</html>