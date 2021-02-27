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

<%--    <ul>--%>
<%--        <li>--%>
<%--            <fmt:message key="invoiceDetails.code" bundle="${legend}"/>--%>
<%--            <b><i><c:out value="${invoice.invoiceCode}"/></i></b>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <fmt:message key="invoiceDetails.customer" bundle="${legend}"/>--%>
<%--            <b><i><c:out value="${invoice.userName}"/></i></b>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <fmt:message key="invoiceDetails.date" bundle="${legend}"/>--%>
<%--            <b><i><fmt:formatDate type="both" value="${invoice.date}" /></i></b>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <fmt:message key="invoiceDetails.total" bundle="${legend}"/>--%>
<%--            <b><i>--%>
<%--                <fmt:formatNumber value="${invoice.cost}" maxFractionDigits="2" minFractionDigits="2"/>--%>
<%--                <fmt:message key="currency" bundle="${legend}"/>--%>
<%--            </i></b>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <fmt:message key="invoiceDetails.payed" bundle="${legend}"/>--%>
<%--            <c:if test="${invoice.paid}">--%>
<%--                <b><i><fmt:message key="invoiceDetails.payedYes" bundle="${legend}"/></i></b>--%>
<%--            </c:if>--%>
<%--            <c:if test="${!invoice.paid}">--%>
<%--                <b><i><fmt:message key="invoiceDetails.payedNo" bundle="${legend}"/></i></b>--%>
<%--            </c:if>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <fmt:message key="invoiceDetails.status" bundle="${legend}"/>--%>
<%--            <c:if test="${invoice.status == 'CREATED'}">--%>
<%--                <b><i><fmt:message key="invoiceDetails.creared" bundle="${legend}"/></i></b>--%>
<%--            </c:if>--%>
<%--            <c:if test="${invoice.status == 'FINISHED'}">--%>
<%--                <b><i><fmt:message key="invoiceDetails.finished" bundle="${legend}"/></i></b>--%>
<%--            </c:if>--%>
<%--            <c:if test="${invoice.status == 'CANCELLED'}">--%>
<%--                <b><i><fmt:message key="invoiceDetails.cancelled" bundle="${legend}"/></i></b>--%>
<%--            </c:if>--%>
<%--        </li>--%>
<%--        <li>--%>
<%--            <fmt:message key="invoiceDetails.notes" bundle="${legend}"/>--%>
<%--            <b><i><c:out value="${invoice.invoiceNotes}"/></i></b>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--    <h3><fmt:message key="invoiceDetails.details" bundle="${legend}"/></h3>--%>
<%--    <table class="widetable">--%>
<%--        <tr>--%>
<%--            <th class="tdc"><fmt:message key="invoiceDetails.table.col1" bundle="${legend}"/></th>--%>
<%--            <th class="tdc"><fmt:message key="invoiceDetails.table.col2" bundle="${legend}"/></th>--%>
<%--            <th class="tdc"><fmt:message key="invoiceDetails.table.col3" bundle="${legend}"/></th>--%>
<%--            <th class="tdc"><fmt:message key="invoiceDetails.table.col4" bundle="${legend}"/></th>--%>
<%--            <th></th>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${invoice.payments}" var="payment">--%>
<%--            <tr>--%>
<%--                <td class="tdl">${payment.key}</td>--%>
<%--                <td class="tdl">${payment.value.quantity}</td>--%>
<%--                <td class="tdc">--%>
<%--                    <fmt:formatNumber value="${payment.value.paymentValue}" maxFractionDigits="2" minFractionDigits="2"/>--%>
<%--                    <fmt:message key="currency" bundle="${legend}"/>--%>
<%--                </td>--%>
<%--                <td class="tdl">${payment.value.paymentNotes}</td>--%>
<%--                <td class="tdc">--%>
<%--                    <form name="remove" method="post" action="project" >--%>
<%--                        <input type="hidden" name="command" value="removeProductFromInvoice" />--%>
<%--                        <input type="hidden" name="invCode" value="${payment.value.orderCode}" />--%>
<%--                        <input type="hidden" name="productCode" value="${payment.key}" />--%>
<%--                        <c:if test="${!invoice.paid && invoice.status == 'CREATED'}">--%>
<%--                            <button type="submit" class="smallbutton">--%>
<%--                                <fmt:message key="invoiceDetails.remove" bundle="${buttons}"/>--%>
<%--                            </button>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${invoice.paid || invoice.status != 'CREATED'}">--%>
<%--                            <button type="submit" class="smallbutton" disabled>--%>
<%--                                <fmt:message key="invoiceDetails.remove" bundle="${buttons}"/>--%>
<%--                            </button>--%>
<%--                        </c:if>--%>
<%--                    </form>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        <c:if test="${!invoice.paid && invoice.status == 'CREATED'}">--%>
<%--        <form name="remove" method="post" action="project" >--%>
<%--            <tr>--%>
<%--                <td class="tdl">--%>
<%--                    <select name="productCode" required>--%>
<%--                        <c:forEach items="${products}" var="product">--%>
<%--                            <option><c:out value="${product}"/></option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td class="tdl"><input class="input" type="text" name="quantity" size="8" required /></td>--%>
<%--                <td class="tdc"></td>--%>
<%--                <td class="tdl"><input class="input" type="text" name="paymentNotes" size="24" /></td>--%>
<%--                <td class="tdc">--%>
<%--                    <input type="hidden" name="command" value="addNewPayment" />--%>
<%--                    <input type="hidden" name="orderCode" value="${invoice.invoiceCode}" />--%>
<%--                    <button type="submit" class="smallbutton">--%>
<%--                        <fmt:message key="invoiceDetails.add" bundle="${buttons}"/>--%>
<%--                    </button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </form>--%>
<%--        </c:if>--%>
<%--    </table>--%>
<%--    <div class="button_div">--%>
<%--        <form action="project" method="post">--%>
<%--            <input type="hidden" name="command" value="cancelInvoice" />--%>
<%--            <input type="hidden" name="invCode" value="${invoice.invoiceCode}" />--%>
<%--            <c:if test="${invoice.status != 'CREATED'}">--%>
<%--                <button type="submit" class="bigbutton" disabled>--%>
<%--                    <fmt:message key="invoiceDetails.cancel" bundle="${buttons}"/>--%>
<%--                </button>--%>
<%--            </c:if>--%>
<%--            <c:if test="${invoice.status == 'CREATED'}">--%>
<%--                <button type="submit" class="bigbutton">--%>
<%--                    <fmt:message key="invoiceDetails.cancel" bundle="${buttons}"/>--%>
<%--                </button>--%>
<%--            </c:if>--%>
<%--        </form>--%>
<%--        <form action="project" method="post">--%>
<%--            <input type="hidden" name="command" value="confirmPayment" />--%>
<%--            <input type="hidden" name="invCode" value="${invoice.invoiceCode}" />--%>
<%--            <c:if test="${invoice.status != 'CREATED' || invoice.paid == true}">--%>
<%--                <button type="submit" class="bigbutton" disabled>--%>
<%--                    <fmt:message key="invoiceDetails.confirm" bundle="${buttons}"/>--%>
<%--                </button>--%>
<%--            </c:if>--%>
<%--            <c:if test="${invoice.status == 'CREATED' && invoice.paid == false}">--%>
<%--                <button type="submit" class="bigbutton">--%>
<%--                    <fmt:message key="invoiceDetails.confirm" bundle="${buttons}"/>--%>
<%--                </button>--%>
<%--            </c:if>--%>
<%--        </form>--%>
<%--        <form action="project" method="post">--%>
<%--            <input type="hidden" name="command" value="closeInvoice" />--%>
<%--            <input type="hidden" name="invCode" value="${invoice.invoiceCode}" />--%>
<%--            <c:if test="${invoice.paid && invoice.status == 'CREATED'}">--%>
<%--                <button type="submit" class="bigbutton">--%>
<%--                    <fmt:message key="invoiceDetails.close" bundle="${buttons}"/>--%>
<%--                </button>--%>
<%--            </c:if>--%>
<%--            <c:if test="${!(invoice.paid && invoice.status == 'CREATED')}">--%>
<%--                <button type="submit" class="bigbutton" disabled>--%>
<%--                    <fmt:message key="invoiceDetails.close" bundle="${buttons}"/>--%>
<%--                </button>--%>
<%--            </c:if>--%>
<%--        </form>--%>
<%--    </div>--%>
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