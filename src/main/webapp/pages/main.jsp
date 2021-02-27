<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/bodytag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${sessionScope.get(\"cart\")}" var="userCart" scope="page" />
<c:set value="${sessionScope.get(\"user\")}" var="user" scope="page" />
<c:set value="${pageScope.get(\"pageNum\")}" var="pageNum" scope="page" />
<c:set value="${sessionScope.get(\"locale\").language}" var="lang" scope="page" />
<fmt:setLocale value="${sessionScope.get(\"locale\")}" />
<fmt:setBundle basename = "legend" var = "legend"/>
<fmt:setBundle basename = "menu" var = "menu"/>
<fmt:setBundle basename = "buttons" var = "buttons"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key = "main.title" bundle = "${legend}"/></title>
</head>
<style>
    <%@include file='main_style.css' %>
</style>

<body>
    <h1><fmt:message key = "main.h1" bundle = "${legend}"/></h1>
    <div class="langmenu">
        <div class="langmenu_item"><c:out value="${user.name}, ${user.userRole}"/></div>
        <c:if test="${lang=='ru'}">
            <form class="langmenu_item" method="get" action="project">
                <input type="hidden" name="command" value="changeLang" />
                <input type="hidden" name="lang" value="ru" />
                <input type="hidden" name="returnPage" value="main" />
                <input type="hidden" name="pageNum" value="${pageNum}" />
                <button class="lang_button_active" type="submit">Ru</button>
            </form>
            <form class="langmenu_item" method="get" action="project">
                <input type="hidden" name="command" value="changeLang" />
                <input type="hidden" name="lang" value="en" />
                <input type="hidden" name="returnPage" value="main" />
                <input type="hidden" name="pageNum" value="${pageNum}" />
                <button class="lang_button" type="submit">En</button>
            </form>
        </c:if>
        <c:if test="${lang == 'en'}">
            <form class="langmenu_item" method="get" action="project">
                <input type="hidden" name="command" value="changeLang" />
                <input type="hidden" name="lang" value="ru" />
                <input type="hidden" name="returnPage" value="main" />
                <input type="hidden" name="pageNum" value="${pageNum}" />
                <button class="lang_button" type="submit">Ru</button>
            </form>
            <form class="langmenu_item" method="get" action="project">
                <input type="hidden" name="command" value="changeLang" />
                <input type="hidden" name="lang" value="en" />
                <input type="hidden" name="returnPage" value="main" />
                <input type="hidden" name="pageNum" value="${pageNum}" />
                <button class="lang_button_active" type="submit">En</button>
            </form>
        </c:if>
    </div>
    <div class="widemenu">
        <c:if test="${user.name == 'Guest'}">
            <form class="menuitem" name="addProductForm" method="get" action="project">
                <input type="hidden" name="command" value="openRegistrationPage" />
                <button class="menubutton" type="submit">
                    <fmt:message key = "main.register" bundle = "${menu}"/>
                </button>
            </form>
            <form class="menuitem" name="addProductForm" method="get" action="project">
                <input type="hidden" name="command" value="openLoginPage" />
                <button class="menubutton" type="submit">
                    <fmt:message key = "main.login" bundle = "${menu}"/>
                </button>
            </form>
        </c:if>
        <c:if test="${user.name != 'Guest'}">
            <c:if test="${user.userRole == 'ROLE_ADMIN'}">
                <form class="menuitem" name="addProductForm" method="get" action="project">
                    <input type="hidden" name="command" value="openNewEventPage" />
                    <button class="menubutton" type="submit">
                        <fmt:message key="main.administration" bundle="${menu}"/>
                    </button>
                </form>
            </c:if>
            <form class="menuitem" name="addProductForm" method="post" action="project">
                <input type="hidden" name="command" value="logout" />
                <button class="menubutton" type="submit">
                    <fmt:message key="main.logout" bundle="${menu}"/>
                </button>
            </form>
        </c:if>
    </div>
    <div>
        <table class="widetable">
            <tr>
                <th style="width: 5%;">#</th>
                <th style="width: 5%;"><fmt:message key = "main.table.col1" bundle = "${legend}"/></th>
                <th style="width: 20%;"><fmt:message key = "main.table.col2" bundle = "${legend}"/></th>
<%--                <th style="width: 20%;"><fmt:message key = "main.table.col3" bundle = "${legend}"/></th>--%>
<%--                <th style="width: 5%;"><fmt:message key = "main.table.col5" bundle = "${legend}"/></th>--%>
<%--                <th style="width: 10%;"><fmt:message key = "main.table.col6" bundle = "${legend}"/></th>--%>
<%--                <th></th>--%>
            </tr>
            <c:forEach items="${products}" var="product" varStatus="loop">
            <tr>
                <td class="tdc"><c:out value="${loop.index + 1}"/></td>
                <td class="tdc"><c:out value="${product.title}"/></td>
                <td class="tdl"><c:out value="${product.dateTime}"/></td>
                <td class="tdc">
                    <form name="eventDetails" method="post" action="project" >
                        <input type="hidden" name="command" value="openEventDetailsPage" />
                        <input type="hidden" name="id" value="${product.id}">
                        <button class="smallbutton">
                            <fmt:message key="main.eventDetails" bundle="${buttons}"/>
                        </button>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
        <div class="pagination">
            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:if test="${i==pageNum}">
                    <a class="active" href="/project?command=main&pageNum=${i}">${i}</a>
                </c:if>
                <c:if test="${i!=pageNum}">
                    <a href="/project?command=main&pageNum=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <t:colontitle />
</body>
</html>