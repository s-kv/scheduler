<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div>
    <table>
	<tr class="header"><td>
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                          <span class="icon-bar"></span>
                          <span class="icon-bar"></span>
                          <span class="icon-bar"></span>
                        </a>
                        <a class="brand" href="#">НаПриём</a

                        <div class="nav-collapse collapse">
                            <c:choose>
                                <c:when test="${not empty loggedUser.fullName}">
                                    <p class="navbar-text pull-right">
                                        Пользователь: <a href="/scheduler/logged/home" class="navbar-link">${loggedUser.fullName}</a>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p class="navbar-text pull-right">
                                        <a href="/scheduler/logged/home" class="navbar-link">Войти</a>
                                    </p>
                                </c:otherwise>
                            </c:choose>    
                            <% if (!request.getAttribute("javax.servlet.forward.request_uri").toString().endsWith("welcome")) { %>
                                <form class="navbar-form" style="float: right; margin-right: 3%;"
                                    action="/scheduler/users/search" method="GET" >
                                    <input  type="text" name="query" placeholder="Имя пользователя">
                                    <button type="submit" class="btn btn-small btn-warning">Поиск</button>
                                </form>
                            <%}%>
                        </div>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
	</td></tr>
    </table>
</div>





