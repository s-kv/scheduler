<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="span10">
    <h3>Поздравляем!</h3>
    <p/> 
    Вы записались на приём к специалисту 
        <a href='<spring:url value="/users/${booking.spec.id}"/>'>${booking.spec.fullName}</a>
    на дату 
        <span style="color:red">${booking.formatStartDate(null)}</span> 
</div>
    
    
