<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="span10">
    <h3>Поиск пользователей по маске '${param.query}'</h3>
    <table  cellpadding="10">
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <div style="height:100px; width:100px; display: table-cell; 
                                vertical-align: middle; text-align: center;">
                        <img src="<spring:url value="/resources/avatars/${user.avatar}"/>">
                    </div>
                </td>
                <td>
                    <table id="innerTable">
                        <tr>
                            <td>Имя:</td>
                            <td class="padding_td">${user.fullName}</td>
                        </tr>
                        <tr>
                            <td>Эл. почта:</td>
                            <td class="padding_td">
                                <a href='<spring:url value="/users/${user.id}"/>'>${user.email}</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Телефон:</td>
                            <td class="padding_td">${user.phone}</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>