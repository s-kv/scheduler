<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="span10">
    <h3>${pageHeader}</h3>
    <table class="simple-little-table">
        <tr>
            <td>Аватарка</td>
            <td>Имя</td>
            <td>Эл. почта</td>
            <td>Телефон</td>
            <td>Дата и время</td>
            <td>Комментарий</td>
        </tr>        
        <c:forEach items="${bookings}" var="booking">
            <tr>
                <c:choose>
                    <c:when test="${specInfo}">
                        <td>
                           <div style="height:100px; width:100px; display: table-cell; 
                                       vertical-align: middle; text-align: center;">
                                <img src="<spring:url value="/resources/avatars/${booking.spec.avatar}"/>">
                           </div>
                        </td>
                        <td>${booking.spec.fullName}</td>
                        <td>
                            <a href='<spring:url value="/users/${booking.spec.id}"/>'>${booking.spec.email}</a>
                        </td>
                       <td>${booking.spec.phone}</td>                        
                    </c:when>
                    <c:otherwise>
                        <td>
                           <div style="height:100px; width:100px; display: table-cell; 
                                       vertical-align: middle; text-align: center;">
                                <img src="<spring:url value="/resources/avatars/${booking.client.avatar}"/>">
                           </div>
                        </td>
                        <td>${booking.client.fullName}</td>
                        <td>
                            <a href='<spring:url value="/users/${booking.client.id}"/>'>${booking.client.email}</a>
                        </td>
                       <td>${booking.client.phone}</td>                        
                    </c:otherwise>
                </c:choose>
   
                <td>${booking.formatStartDate(null)}</td>
                <td>${booking.note}</td>
            </tr>
        </c:forEach>
    </table    
</div>