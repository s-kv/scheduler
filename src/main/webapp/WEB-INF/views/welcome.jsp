<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="navbar" style="display: flex; align-items: center; justify-content: center;">
    <sf:form class="navbar-form" method="GET" action="/scheduler/users/search">
        <input type="text" name="query" class="input-xxlarge inline">
        <button type="submit" class="btn btn-warning inline">Поиск</button>
    </sf:form>
</div>