<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/bodytag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.get(\"locale\")}" />
<fmt:setBundle basename="legend" var="legend"/>
<fmt:setBundle basename="menu" var="menu"/>
<fmt:setBundle basename="buttons" var="buttons"/>

<html>
<head>
    <title><fmt:message key="invoiceDetails.title" bundle="${legend}"/></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/css/tempusdominus-bootstrap-4.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
</head>
<style>
    <%@include file='admin_style.css' %>
</style>
<body>
<h1><fmt:message key="invoiceDetails.h1" bundle="${legend}"/></h1>
<div class="widemenu">
    <p><c:out value="${user.name}, ${user.userRole}"/></p>
    <form name="addProductForm" method="post" action="project" class="menuitem">
        <input type="hidden" name="command" value="manageInvoices" />
        <input type="hidden" name="type" value="all" />
        <button type="submit" class="menubutton">
            <fmt:message key="invoiceDetails.back" bundle="${menu}"/>
        </button>
    </form>
    <form name="addProductForm" method="post" action="project" class="menuitem">
        <input type="hidden" name="command" value="administration" />
        <button type="submit" class="menubutton">
            <fmt:message key="invoiceDetails.administration" bundle="${menu}"/>
        </button>
    </form>
</div>
<div class="inner_div">
    <h3><fmt:message key="invoiceDetails.req" bundle="${legend}"/></h3>
    <div class="card-header">
        <c:out value="${event.title}"/>
    </div>
    <div class="card-body">
        <h5 class="card-title"></h5>
        <p class="text-justify">
            Starts on <c:out value="${event.dateTime}"/>
        </p>
        <c:forEach items="${topics}" var="topic">
            <div class="card-text">
                <p>
                    <c:out value="${topic.title}"/>
                </p>
                <c:if test="${user.userRole == 'ROLE_ADMIN' || user.userRole == 'ROLE_SPEAKER'}">
                    <form method="POST">
                        <input type="hidden" name="command" value="updateTopic" />
                        <input type="hidden" value="${topic.id}" name="id"/>
                        <input type="hidden" value="${topic.eventId}" name="eventId"/>
                        <label for="updateSpeakerId">Update speaker:</label>
                        <label for="title">Title:</label>
                        <input type="text" name="title" value="${topic.title}" id="title" />
                        <input type="text" name="speakerId" value="${topic.speakerId}" id="updateSpeakerId" />
                        <label for="setApproved">Approved:</label>
                        <input type="checkbox" name="approved" value="${topic.approved}" id="setApproved"<c:if test="${topic.approved}"> checked</c:if> />
                        <br/>
                        <button class="btn btn-primary">
                            Update
                        </button>
                    </form>
                </c:if>
            </div>
        </c:forEach>
        <c:if test="${user.name != 'Guest' && user.userRole == 'ROLE_USER'}">
            <form method="POST">
                <input type="hidden" name="command" value="assignUser" />
                <input type="hidden" name="userId" value="${user.id}" />
                <input type="hidden" name="eventId" value="${event.id}" />
                <button class="btn btn-primary">
                    Assign
                </button>
            </form>
        </c:if>
    </div>
    <c:if test="${user.userRole == 'ROLE_ADMIN'}">
        <div>
            <form method="POST" id="event-form" class="modal">
                <input type="hidden" name="command" value="updateEvent" />
                <input type="hidden" name="id" value="${event.id}" />
                <label for="title">Title:</label>
                <input type="text" name="title" id="eventTitle" value="${event.title}" required />
                <br/>
                <label for="date"><fmt:message key="newProduct.date" bundle="${legend}"/>:</label>
                <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                    <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"
                           name="date" id="date" value="${event.dateTime}" placeholder="Date" required/>
                    <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar-alt"></i></div>
                    </div>
                </div>
                <button class="btn btn-success">
                    Submit
                </button>
            </form>
            <a href="#event-form" rel="modal:open" class="btn btn-warning">Update event</a>
        </div>
        <form method="POST">
            <input type="hidden" name="command" value="deleteEvent" />
            <input type="hidden" name="id" value="${event.id}" />
            <button class="btn btn-danger">
                Delete
            </button>
        </form>
    </c:if>
</div>
<t:colontitle/>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://unpkg.com/@popperjs/core@2.6.0/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://momentjs.com/downloads/moment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
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