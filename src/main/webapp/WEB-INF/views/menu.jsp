<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <c:if test="${not empty loggedUser}">
        <div class="span2">
            <div class="well" style="position : fixed; top:10%; left:2%;">
                <ul class="nav nav-list">
                    <li class="nav-header">Меню пользователя</li>
                    <li><a class="label3" style="padding-bottom: 10px" href="/scheduler/logged/home">Профиль</a></li>
                    <li><a class="label3" style="padding-bottom: 10px" href="/scheduler/booking/bookedToMe">Таблица приёма</a></li>
                    <li><a class="label3" style="padding-bottom: 10px" href="/scheduler/booking/bookedByMe">Ежедневник</a></li>
                </ul>
            </div><!--/.well -->
        </div><!--/span-->
    </c:if>
</div>